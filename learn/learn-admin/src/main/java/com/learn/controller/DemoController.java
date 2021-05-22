package com.learn.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.learn.mapper.UserMapper;
import com.learn.model.User;
import com.learn.model.Users;
import com.learn.service.IUsersService;
import com.learn.webapi.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags="测试")
public class DemoController {

    static {
        System.out.println(1);
    }
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUsersService iUsersService;

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("用户列表")
    public CommonResult<List<User>> echo(String message) {
        Wrapper<Users> queryWrapper = new QueryWrapper<>();
        iUsersService.list(queryWrapper);
        return CommonResult.success(userMapper.selectList(null));
    }
}
