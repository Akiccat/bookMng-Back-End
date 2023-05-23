package com.accenture.powerup.bookmng.service;

import com.accenture.powerup.bookmng.entity.GetLoginLogEntity;
import com.accenture.powerup.bookmng.entity.LoginLogEntity;
import com.accenture.powerup.bookmng.entity.UserEntity;

import java.util.List;

/**
 * 登录业务接口。
 */
public interface LoginService {

    /**
     * 用户登录。
     * <p>根据用户名、密码登录系统</p>
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    public UserEntity login(String userName, String password);

    /**
     * 用户登录日志。
     * <p>查询用户登录日志</p>
     *
     * @return 用户信息
     */
    List<GetLoginLogEntity> getLogs();
}
