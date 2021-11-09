package com.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.dto.PostTagParam;
import com.learn.model.PostTag;
import com.learn.mapper.PostTagMapper;
import com.learn.model.PostTagRelation;
import com.learn.service.IPostTagRelationService;
import com.learn.service.IPostTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements IPostTagService {
    @Autowired
    private IPostTagRelationService postTagRelationService;

    @Override
    public boolean savePostTag(PostTagParam postTagParam) {
        PostTag postTag = new PostTag();
        BeanUtils.copyProperties(postTagParam,postTag);
        boolean result = this.save(postTag);
        if(postTagParam.getObjectId()!=null){
            PostTagRelation postTagRelation = new PostTagRelation();
            postTagRelation.setPostTagId(postTag.getPostId());
            postTagRelation.setObjectId(postTagParam.getObjectId());
            postTagRelation.setTermOrder(postTagParam.getTermOrder());
            postTagRelationService.save(postTagRelation);
        }
        return result;
    }

    @Override
    public List<PostTag> getByObjectId(Long objectId) {
        QueryWrapper<PostTagRelation> postTagRelationQueryWrapper = new QueryWrapper<>();
        postTagRelationQueryWrapper.eq("object_id",objectId);
        List <PostTagRelation> postTagRelationList = postTagRelationService.list(postTagRelationQueryWrapper);
        List<Long> postTagIdList = postTagRelationList.stream().map(PostTagRelation::getPostTagId).collect(Collectors.toList());
        QueryWrapper<PostTag> postTagQueryWrapper = new QueryWrapper();
        postTagQueryWrapper.in("post_id",postTagIdList);
        return this.list(postTagQueryWrapper);
    }
}
