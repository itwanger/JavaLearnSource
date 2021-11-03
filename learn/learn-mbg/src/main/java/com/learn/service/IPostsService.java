package com.learn.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.learn.dto.PostsPageQueryParam;
import com.learn.dto.PostsParam;
import com.learn.model.Posts;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.vo.PostsVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
public interface IPostsService extends IService<Posts> {
    /**
     * 保存内容
     * @param postsParam
     * @return
     */
    boolean savePosts(PostsParam postsParam);

    /**
     * 修改内容
     * @param postsParam
     * @return
     */
    boolean updatePosts(PostsParam postsParam);

    boolean removePostsById(Long id);


    IPage<PostsVo> findByPage(PostsPageQueryParam postsPageQueryParam);
}
