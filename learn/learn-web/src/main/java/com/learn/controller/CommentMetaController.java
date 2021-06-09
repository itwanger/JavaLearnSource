package com.learn.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.mapper.UserMapper;
import com.learn.model.User;
import com.learn.model.Users;
import com.learn.service.IUsersService;
import com.learn.webapi.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 文章评论额外信息表 前端控制器
 * </p>
 *
 * @author 石磊
 * @since 2021-05-22
 */
@Controller
@RequestMapping("/commentMeta")
@Api(tags="文章评论额外信息")
public class CommentMetaController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUsersService iUsersService;

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("用户列表")
    public ResultObject<List<User>> echo(String message) {
        Wrapper<Users> queryWrapper = new QueryWrapper<>();
        iUsersService.list(queryWrapper);
        return ResultObject.success(userMapper.selectList(null));
    }
}

