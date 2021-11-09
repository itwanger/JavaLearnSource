package com.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.dto.PostsPageQueryParam;
import com.learn.dto.PostsParam;
import com.learn.model.Posts;
import com.learn.mapper.PostsMapper;
import com.learn.model.TermRelationships;
import com.learn.service.IPostsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.service.ITermRelationshipsService;
import com.learn.service.IUsersService;
import com.learn.util.TermRelationType;
import com.learn.vo.PostsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements IPostsService {
    @Autowired
    private IUsersService iUsersService;
    @Autowired
    private ITermRelationshipsService iTermRelationshipsService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean savePosts(PostsParam postsParam) {
        Posts posts = new Posts();
        BeanUtils.copyProperties(postsParam,posts);
        posts.setCommentCount(0L);
        if(posts.getPostDate() == null){
            posts.setPostDate(new Date());
        }
        posts.setPostAuthor(iUsersService.getCurrentUserId());
        this.save(posts);
        return insertTermRelationships(postsParam,posts);
    }



    @Override
    public boolean updatePosts(PostsParam postsParam) {
        Posts posts = this.getById(postsParam.getId());
        Date publishDate = posts.getPostDate();
        BeanUtils.copyProperties(postsParam,posts);
        //防止修改发布时间
        posts.setPostDate(publishDate);
        posts.setPostModified(new Date());

        QueryWrapper<TermRelationships> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("object_id",postsParam.getId());
        queryWrapper.eq("term_taxonomy_id",postsParam.getTermTaxonomyId());
        int count = iTermRelationshipsService.count(queryWrapper);
        // 关系不能重复
        if(count ==0){
            return insertTermRelationships(postsParam,posts);
        }

        return true;
    }

    @Override
    public boolean removePostsById(Long id) {
        this.removeById(id);
        QueryWrapper<TermRelationships> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("object_id",id);

        return  iTermRelationshipsService.remove(queryWrapper);
    }

    @Override
    public IPage<PostsVo> findByPage(PostsPageQueryParam postsPageQueryParam) {
        QueryWrapper<PostsPageQueryParam> queryWrapper = new QueryWrapper<>();
        if(postsPageQueryParam.getPostType()!=null){
            queryWrapper.eq("a.post_type",postsPageQueryParam.getPostType().toString());
        }
        if(postsPageQueryParam.getTermTaxonomyId()!=null){
            queryWrapper.eq("b.term_taxonomy_id",postsPageQueryParam.getTermTaxonomyId());
        }
        Page<PostsVo> postsPage = new Page<>(postsPageQueryParam.getPage(), postsPageQueryParam.getPageSize());

        return this.getBaseMapper().findByPage(postsPage,queryWrapper);
    }

    private boolean insertTermRelationships(PostsParam postsParam, Posts posts){
        TermRelationships termRelationships  = new TermRelationships();
        termRelationships.setTermTaxonomyId(postsParam.getTermTaxonomyId());
        termRelationships.setObjectId(posts.getId());
        termRelationships.setTermOrder(postsParam.getMenuOrder());
        termRelationships.setType(TermRelationType.CONTENT.getType());
        return iTermRelationshipsService.save(termRelationships);
    }
}
