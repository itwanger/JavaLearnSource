package com.learn.service;

import com.learn.dto.PostsParam;
import com.learn.model.Posts;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
