package com.learn.service.impl;

import com.learn.model.PostTag;
import com.learn.mapper.PostTagMapper;
import com.learn.service.IPostTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
