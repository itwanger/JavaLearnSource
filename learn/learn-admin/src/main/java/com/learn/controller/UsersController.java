package com.learn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.model.Users;
import com.learn.service.IUsersService;
import com.learn.webapi.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 用户表 前端控制器
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Controller
@Api(tags="用户")
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加用户")
    public ResultObject<String> insert(@Valid Users users) {
        users.setUserRegistered(new Date());
        return ResultObject.success(usersService.save(users) ? "保存成功" : "保存失败");
    }

    @RequestMapping(value = "/getById",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据id获取用户")
    public ResultObject<Users> getById(long usersId) {
        return ResultObject.success(usersService.getById(usersId));
    }

    @RequestMapping(value = "/update",method=RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更新")
    public ResultObject<String> update(@Valid Users users) {
        if (users.getId() == null) {
            return ResultObject.failed("id不能为空");
        }
        return ResultObject.success(usersService.updateById(users) ? "更新成功" : "更新失败");
    }

    @RequestMapping(value = "/delete",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("删除")
    public ResultObject<String> delete(long usersId) {
        return ResultObject.success(usersService.removeById(usersId) ? "删除成功" : "删除失败");
    }

    @RequestMapping(value = "/queryPageable",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页查询")
    public ResultObject<Map<String,Object>> queryPageable(long pageSize, long page){
        Map<String,Object> map = new HashMap<>();
        Page<Users> usersPage = new Page<>(page,pageSize);
        IPage<Users> usersIPage = usersService.page(usersPage);
        map.put("list",usersIPage.getRecords());
        map.put("total",usersIPage.getTotal());
        return ResultObject.success(map);
    }
}

