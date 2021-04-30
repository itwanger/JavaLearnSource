package com.learn.controller;

import com.learn.webapi.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    static {
        System.out.println(1);
    }

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> echo(String message) {
        return CommonResult.success("success");
    }
}
