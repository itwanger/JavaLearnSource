package com.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.model.Site;
import com.learn.mapper.SiteMapper;
import com.learn.model.TermTaxonomy;
import com.learn.model.UserSite;
import com.learn.model.Users;
import com.learn.service.ISiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.service.ITermTaxonomyService;
import com.learn.service.IUserSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 站点 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements ISiteService {
    @Autowired
    private ITermTaxonomyService iTermTaxonomyService;
    @Autowired
    private IUserSiteService userSiteService;
    @Override
    public boolean deleteSite(long siteId) {
        QueryWrapper<UserSite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("site_id", siteId);
        int  userSiteCount = userSiteService.count(queryWrapper);
        if(userSiteCount>0){
            return false;
        }
        QueryWrapper<TermTaxonomy> taxonomyQueryWrapper = new QueryWrapper<>();
        queryWrapper.eq("site_id", siteId);
        int  taxonomyCount = iTermTaxonomyService.count(taxonomyQueryWrapper);
        if(taxonomyCount>0){
            return false;
        }
        return this.removeById(siteId);
    }
}
