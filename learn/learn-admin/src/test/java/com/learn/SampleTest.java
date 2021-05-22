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
        Wrapper<Users> queryWrapper = new QueryWrapper<>();
        Users users = new Users();
        users.setUserRegistered(new Date());
        users.setDisplayName("231");
        iUsersService.save(users);
        List<Users> userList = iUsersService.list(queryWrapper);
        System.out.println(("----- selectAll method test ------"));
////      /*  List<Users> userList = userMapper.selectList(null);
//        userList.forEach(System.out::println);*/
        userList.stream().forEach((k) -> {
            LOGGER.info(k.getDisplayName());
        });
    }

}
