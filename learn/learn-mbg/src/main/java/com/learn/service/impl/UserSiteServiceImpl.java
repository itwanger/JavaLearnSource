package com.learn.service.impl;

import com.learn.model.UserSite;
import com.learn.mapper.UserSiteMapper;
import com.learn.service.IUserSiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户站点关联关系表 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Service
public class UserSiteServiceImpl extends ServiceImpl<UserSiteMapper, UserSite> implements IUserSiteService {

}
