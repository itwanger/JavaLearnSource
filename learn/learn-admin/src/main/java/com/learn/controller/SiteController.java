package com.learn.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import com.learn.model.Site;
import com.learn.service.ISiteService;
import com.learn.webapi.CommonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 站点 前端控制器
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Controller
@Api(tags="站点")
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private ISiteService siteService;

    @RequestMapping(value = "/insert",method=RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加站点")
    public CommonResult<String> insert(@Valid   Site site){
        int i = 1/0;
        return CommonResult.success( siteService.save(site)?"保存成功":"保存失败");
    }
}

