package com.learn.service;

import com.learn.model.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.vo.UpdateAdminPasswordParam;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 石磊
 * @since 2021-09-12
 */
public interface IUsersService extends IService<Users> {
    /**
     * 根据用户名获取后台管理员
     */
    Users getAdminByUsername(String username);

    /**
     * 注册功能
     */
    Users register(Users users);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

}
