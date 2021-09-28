package com.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.model.Users;
import com.learn.mapper.UsersMapper;
import com.learn.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.util.UserStatus;
import com.learn.util.UserType;
import com.learn.vo.UpdateAdminPasswordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Override
    public Users getAdminByUsername(String username) {
        return null;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean register(Users users) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userLogin",users.getUserLogin());
        int count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0){
            return false;
        }
        users.setUserRegistered(new Date());
        users.setUserType(UserType.BACKEND.getUserType());
        users.setUserStatus(UserStatus.ENABLE.getStatus());
        String encodePassword = passwordEncoder.encode(users.getUserPass());
        users.setUserPass(encodePassword);

        return save(users);
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updatePasswordParam) {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
