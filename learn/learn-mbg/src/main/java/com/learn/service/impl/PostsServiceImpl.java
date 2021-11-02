package com.learn.service.impl;

import com.learn.dto.PostsParam;
import com.learn.model.Posts;
import com.learn.mapper.PostsMapper;
import com.learn.model.TermRelationships;
import com.learn.service.IPostsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.service.ITermRelationshipsService;
import com.learn.service.IUsersService;
import com.learn.util.TermRelationType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
        TermRelationships termRelationships  = new TermRelationships();
        BeanUtils.copyProperties(postsParam,posts);
        posts.setCommentCount(0L);
        if(posts.getPostDate() == null){
            posts.setPostDate(new Date());
        }

        posts.setPostAuthor(iUsersService.getCurrentUserId());
        this.save(posts);
        termRelationships.setTermTaxonomyId(postsParam.getTermTaxonomyId());
        termRelationships.setObjectId(posts.getId());
        termRelationships.setTermOrder(postsParam.getMenuOrder());
        termRelationships.setType(TermRelationType.CONTENT.getType());
        return iTermRelationshipsService.save(termRelationships);
    }
}
