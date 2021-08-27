package com.learn.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags="学习网站前端入口")
public class LearnWebFacadeController {
    private static Logger LOGGER = LoggerFactory.getLogger(LearnWebFacadeController.class);

    /**
     * 首页
     * @return
     */
    public String index(){
        return "";
    }


    /**
     * 首页页入口（内网）
     */
    @RequestMapping(value = {"/c/{site:[0-9A-Za-z]+}/"}, method = RequestMethod.GET)
    public String intranetIndex(@PathVariable String site, HttpServletRequest request,
                                HttpServletResponse response, ModelMap model)  {
        return null;
//        return index(request, response, model);
    }

    /**
     * 内容动态页入口（外网）
     */
    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}/{id:[0-9]+}.html"}, method = RequestMethod.GET)
    public String content(@PathVariable String path, @PathVariable Integer id, HttpServletRequest request,
                          HttpServletResponse response, ModelMap model) {
        return null;
//        return content(id, 1, request, response, model);
    }

    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}/{id:[0-9]+}"}, method = RequestMethod.GET)
    public String contentNoSuffix(@PathVariable String path, @PathVariable Integer id, HttpServletRequest request,
                                  HttpServletResponse response, ModelMap model) {
        return null;
//    return content(id, 1, request, response, model);
    }

    /**
     * 内容动态分页入口（外网）
     */
    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}/{id:[0-9]+}_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String contentPage(@PathVariable String path, @PathVariable Integer id, @PathVariable Integer page,
                              HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return null;
//     return content(id, page, request, response, model);
    }

    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}/{id:[0-9]+}_{page:[0-9]+}"}, method = RequestMethod.GET)
    public String contentPageNoSuffix(@PathVariable String path, @PathVariable Integer id, @PathVariable Integer page,
                                      HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return null;
//    return content(id, page, request, response, model);
    }


    /**
     * 内容动态分页入口(内网)
     */
    @RequestMapping(value = {
            "/c/{site:[0-9A-Za-z]+}/{path:[0-9A-Za-z]+}/{/{id:[0-9]+}_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String intranetContentPage(@PathVariable Integer id, @PathVariable String path, @PathVariable Integer page,
                                      HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return null;
//     return content(id, page, request, response, model);
    }

    @RequestMapping(value = {
            "/c/{site:[0-9A-Za-z]+}/{path:[0-9A-Za-z]+}/{/{id:[0-9]+}_{page:[0-9]+}"}, method = RequestMethod.GET)
    public String intranetContentPageNoSuffix(@PathVariable Integer id, @PathVariable String path, @PathVariable Integer page,
                                              HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return null;
//    return content(id, page, request, response, model);
    }

    /**
     * 内容动态页入口（内网）
     */
    @RequestMapping(value = {"/c/{site:[0-9A-Za-z]+}/{path:[0-9A-Za-z]+}/{id:[0-9]+}.html"}, method = RequestMethod.GET)
    public String intranetContent(@PathVariable String site, @PathVariable String path, @PathVariable Integer id,
                                  HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return null;
//    return content(id, 1, request, response, model);
    }

   


    /**
     * 内容动态分页入口（内网）
     */
    @RequestMapping(value = {"/c/{site:[0-9A-Za-z]+}/{path:[0-9A-Za-z]+}/{id:[0-9]+}_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String intranetContentPage(@PathVariable String path, @PathVariable Integer id, @PathVariable Integer page,
                                      HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return null;
//    return content(id, page, request, response, model);
    }

  

    /**
     * 栏目动态页入口(外网)
     */
    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}.html"}, method = RequestMethod.GET)
    public String channel(@PathVariable String path, HttpServletRequest request, HttpServletResponse response,
                          ModelMap model) /*throws GlobalException */{
        return null;
//     return channel(path, 1, request, response, model);
    }



    /**
     * 栏目动态分页入口(外网)
     */
    @RequestMapping(value = {"/{path:[0-9A-Za-z]+}_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String channelPage(@PathVariable String path, @PathVariable Integer page, HttpServletRequest request,
                              HttpServletResponse response, ModelMap model) /*throws GlobalException*/ {
        return null;
//    return channel(path, page, request, response, model);
    }

    

    /**
     * 栏目动态页入口（内网）
     */
    @RequestMapping(value = {"/c/{site:[0-9A-Za-z]+}/{path:[0-9A-Za-z]+}.html"}, method = RequestMethod.GET)
    public String intranetChannel(@PathVariable String site, @PathVariable String path, HttpServletRequest request,
                                  HttpServletResponse response, ModelMap model)  {
        return null;
// return channel(path, 1, request, response, model);
    }

  

    /**
     * 栏目动态分页入口（内网）
     */
    @RequestMapping(value = {"/c/{site:[0-9A-Za-z]+}/{path:[0-9A-Za-z]+}_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String intranetChannelPage(@PathVariable String site, @PathVariable String path, @PathVariable Integer page,
                                      HttpServletRequest request, HttpServletResponse response, ModelMap model)  {
        return null;
// return channel(path, page, request, response, model);
    }

   


}
