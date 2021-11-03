package com.learn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.dto.PostsPageQueryParam;
import com.learn.dto.PostsParam;
import com.learn.model.Posts;
import com.learn.service.IPostsService;
import com.learn.service.IUsersService;
import com.learn.util.PostStatus;
import com.learn.util.PostType;
import com.learn.vo.PostsVo;
import com.learn.webapi.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Controller
@Api(tags = "文章 ")
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private IPostsService postsService;
    @Autowired
    private IUsersService iUsersService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加文章")
    public ResultObject<String> insert(@Valid PostsParam postsParam) {
        PostStatus postStatus = PostStatus.valueOf(postsParam.getPostStatus());
        if (postStatus == null || postStatus == PostStatus.DELETED) {
            return ResultObject.failed("文章状态设置错误");
        }
        PostType postType = PostType.valueOf(postsParam.getPostType());
        if (postType == null) {
            return ResultObject.failed("文章类型设置错误");
        }
        return ResultObject.success(postsService.savePosts(postsParam) ? "保存成功" : "保存失败");
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据id获取文章")
    public ResultObject<Posts> getById(long postsId) {
        return ResultObject.success(postsService.getById(postsId));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更新")
    public ResultObject<String> update(@Valid PostsParam postsParam) {
        if (postsParam.getId() == null) {
            return ResultObject.failed("id不能为空");
        }
        PostStatus postStatus = PostStatus.valueOf(postsParam.getPostStatus());
        if (postStatus == null || postStatus == PostStatus.DELETED) {
            return ResultObject.failed("文章状态设置错误");
        }
        PostType postType = PostType.valueOf(postsParam.getPostType());
        if (postType == null) {
            return ResultObject.failed("文章类型设置错误");
        }
        return ResultObject.success(postsService.updatePosts(postsParam) ? "更新成功" : "更新失败");
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("删除")
    public ResultObject<String> delete(long postsId) {
        return ResultObject.success(postsService.removePostsById(postsId) ? "删除成功" : "删除失败");
    }

    @RequestMapping(value = "/queryPageable", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询")
    public ResultObject<Map<String, Object>> queryPageable(PostsPageQueryParam postsPageQueryParam) {
        Map<String, Object> map = new HashMap<>();
        IPage<PostsVo> postsIPage = postsService.findByPage(postsPageQueryParam);
        map.put("items", postsIPage.getRecords());
        map.put("total", postsIPage.getTotal());
        return ResultObject.success(map);
    }

}

