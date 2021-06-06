package com.learn;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.mapper.UserMapper;
import com.learn.model.User;
import com.learn.model.Users;
import com.learn.service.IUsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUsersService iUsersService;
    private static Logger LOGGER = LoggerFactory.getLogger(SampleTest.class);
    @Test
    public void testSelect() {
     
    }

}
