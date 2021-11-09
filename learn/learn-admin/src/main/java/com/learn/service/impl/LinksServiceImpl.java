package com.learn.service.impl;

import com.learn.model.Links;
import com.learn.mapper.LinksMapper;
import com.learn.service.ILinksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 链接信息表 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Service
public class LinksServiceImpl extends ServiceImpl<LinksMapper, Links> implements ILinksService {

}
