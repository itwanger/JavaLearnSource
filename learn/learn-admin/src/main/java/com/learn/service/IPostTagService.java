package com.learn.service;

import com.learn.dto.PostTagParam;
import com.learn.model.PostTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
public interface IPostTagService extends IService<PostTag> {

    boolean savePostTag(PostTagParam postTagParam);

    /**
     * 获取文章标签
     * @param objectId
     * @return
     */
    List<PostTag> getByObjectId(Long objectId);
}
