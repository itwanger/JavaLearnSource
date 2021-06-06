package com.learn.service.impl;

import com.learn.model.Site;
import com.learn.mapper.SiteMapper;
import com.learn.service.ISiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 站点 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements ISiteService {

}