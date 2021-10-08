package com.learn.service;

import com.learn.model.Site;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 站点 服务类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
public interface ISiteService extends IService<Site> {
    /**
     * 删除前进行关联判断
     * @param siteId
     * @return
     */
    boolean deleteSite(long siteId);
}
