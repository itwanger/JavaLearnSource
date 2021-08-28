package com.learn.controller;

import com.learn.service.ILearnWebRequestStrategy;
import com.learn.util.WebRequestParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags="学习网站前端入口")
public class LearnWebFacadeController {
    private static Logger LOGGER = LoggerFactory.getLogger(LearnWebFacadeController.class);
    @Resource(name="channelPageRequestStrategy")
    private  ILearnWebRequestStrategy channelPageRequestStrategy;
    @Resource (name="contentPageRequestStrategy")
    private ILearnWebRequestStrategy contentPageRequestStrategy;

    @Resource (name="indexPageRequestStrategy")
    private  ILearnWebRequestStrategy indexPageRequestStrategy;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    @ApiOperation("首页页入口")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).model(model).build();
        return indexPageRequestStrategy.handleRequest(webRequestParam);
    }


    @ApiOperation("内容动态页入口")
    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}/{id:[0-9]+}.html"}, method = RequestMethod.GET)
    public String content(@PathVariable String path, @PathVariable Integer id, HttpServletRequest request,
                          HttpServletResponse response, ModelMap model) {
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).path(path).id(id).page(1).model(model).build();
        return contentPageRequestStrategy.handleRequest(webRequestParam);
    }




    @ApiOperation("内容动态分页入口")
    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}/{id:[0-9]+}_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String contentPage(@PathVariable String path, @PathVariable Integer id, @PathVariable Integer page,
                              HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).path(path).id(id).model(model).page(page).build();
        return contentPageRequestStrategy.handleRequest(webRequestParam);
    }

    /**
     * 栏目动态页入口(外网)
     */
    @ApiOperation("栏目动态页入口")
    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}.html"}, method = RequestMethod.GET)
    public String channel(@PathVariable String path, HttpServletRequest request, HttpServletResponse response,
                          ModelMap model) /*throws GlobalException */{

        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).path(path).model(model).page(1).build();
        return channelPageRequestStrategy.handleRequest(webRequestParam);
    }



    /**
     * 栏目动态分页入口(外网)
     */
    @ApiOperation("栏目动态分页入口")
    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String channelPage(@PathVariable String path, @PathVariable Integer page, HttpServletRequest request,
                              HttpServletResponse response, ModelMap model) /*throws GlobalException*/ {
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).path(path).model(model).page(1).build();
        return channelPageRequestStrategy.handleRequest(webRequestParam);
    }

    

  


   


}
