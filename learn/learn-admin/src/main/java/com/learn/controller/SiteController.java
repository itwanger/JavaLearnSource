package com.learn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import com.learn.model.Site;
import com.learn.service.ISiteService;
import com.learn.webapi.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 站点 前端控制器
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Controller
@Api(tags = "站点")
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private ISiteService siteService;

    @RequestMapping(value = "/insert",method=RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加站点")
    public ResultObject<String> insert(@Valid Site site) {
//       throw new ApiException("test");
        return ResultObject.success(siteService.save(site) ? "保存成功" : "保存失败");
    }

    @RequestMapping(value = "/getById",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据id获取站点")
    public ResultObject<Site> getById(long siteId) {
        return ResultObject.success(siteService.getById(siteId));
    }

    @RequestMapping(value = "/update",method=RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更新")
    public ResultObject<String> update(@Valid Site site) {
        if (site.getSiteId() == null) {
            return ResultObject.failed("id不能为空");
        }
        return ResultObject.success(siteService.updateById(site) ? "更新成功" : "更新失败");
    }

    @RequestMapping(value = "/delete",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("删除")
    public ResultObject<String> delete(long siteId) {
        return ResultObject.success(siteService.removeById(siteId) ? "删除成功" : "删除失败");
    }

    @RequestMapping(value = "/queryPageable",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询")
    public ResultObject<Map<String,Object>> queryPageable(long pageSize,long page){
        Map<String,Object> map = new HashMap<>();
        Page<Site> sitePage = new Page<>(page,pageSize);
        IPage<Site> siteIPage = siteService.page(sitePage);
        map.put("list",siteIPage.getRecords());
        map.put("total",siteIPage.getTotal());
        return ResultObject.success(map);
    }


}

