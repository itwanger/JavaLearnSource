package com.learn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.dto.PostTagParam;
import com.learn.model.PostTag;
import com.learn.service.IPostTagService;
import com.learn.webapi.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Controller
@Api(tags = "标签")
@RequestMapping("/postTag")
public class PostTagController {
    @Autowired
    private IPostTagService postTagService;

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加标签")
    public ResultObject<String> insert(@Valid PostTagParam postTag) {

        return ResultObject.success(postTagService.savePostTag(postTag) ? "保存成功" : "保存失败");
    }

    @RequestMapping(value = "/getById",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据id获取标签")
    public ResultObject<PostTag> getById(long postTagId) {
        return ResultObject.success(postTagService.getById(postTagId));
    }

    @RequestMapping(value = "/getByObjectId",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据文章内容获取标签")
    public ResultObject<List<PostTag>> getByObjectId(long objectId) {

        return ResultObject.success(postTagService.getByObjectId(objectId));
    }

    @RequestMapping(value = "/getByName",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("模糊匹配")
    public ResultObject<List<PostTag>> getByObjectId(String  keyWord,long siteId) {
        QueryWrapper<PostTag> postTagQueryWrapper = new QueryWrapper();
        postTagQueryWrapper.eq("site_id",siteId);
        postTagQueryWrapper.like("description",keyWord+"%");
        return ResultObject.success(postTagService.list(postTagQueryWrapper));
    }


    @RequestMapping(value = "/delete",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("删除")
    public ResultObject<String> delete(long postTagId) {
        return ResultObject.success(postTagService.removeById(postTagId) ? "删除成功" : "删除失败");
    }

    @RequestMapping(value = "/queryPageable",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询")
    public ResultObject<Map<String,Object>> queryPageable(long pageSize, long page,long siteId){
        Map<String,Object> map = new HashMap<>();
        Page<PostTag> postTagPage = new Page<>(page,pageSize);
        QueryWrapper<PostTag> postTagQueryWrapper = new QueryWrapper();
        postTagQueryWrapper.eq("site_id",siteId);
        IPage<PostTag> postTagIPage = postTagService.page(postTagPage,postTagQueryWrapper);
        map.put("items",postTagIPage.getRecords());
        map.put("total",postTagIPage.getTotal());
        return ResultObject.success(map);
    }
}

