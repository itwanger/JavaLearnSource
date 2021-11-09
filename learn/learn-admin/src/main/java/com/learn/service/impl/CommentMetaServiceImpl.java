package com.learn.service.impl;

import com.learn.model.CommentMeta;
import com.learn.mapper.CommentMetaMapper;
import com.learn.service.ICommentMetaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章评论额外信息表 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Service
public class CommentMetaServiceImpl extends ServiceImpl<CommentMetaMapper, CommentMeta> implements ICommentMetaService {

}
