package com.accenture.powerup.bookmng.repository;

import com.accenture.powerup.bookmng.entity.GetLoginLogEntity;
import org.springframework.stereotype.Repository;

import com.accenture.powerup.bookmng.entity.LoginLogEntity;

import java.util.List;

/**
 * 登录日志Mapper映射器。
 */
@Repository
public interface LoginLogRepository {

    /**
     * 新建登录日志。
     *
     * @param loginLog 登录日志信息
     */
    public void insert(LoginLogEntity loginLog);

    /**
     * 获取登录日志。
     *
     */
    List<GetLoginLogEntity> getLogs();
}
