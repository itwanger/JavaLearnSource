package com.learn.controller;

import com.learn.mapper.UserMapper;
import com.learn.model.User;
import com.learn.webapi.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DemoController {
    static {
        System.out.println(1);
    }
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<User>> echo(String message) {
        return CommonResult.success(userMapper.selectList(null));
    }
}
