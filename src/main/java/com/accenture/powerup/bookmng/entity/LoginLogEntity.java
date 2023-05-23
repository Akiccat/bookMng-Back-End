package com.accenture.powerup.bookmng.entity;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

/**
 * 登录日志实体类。
 */
@Alias("LoginLog")
public class LoginLogEntity extends BaseEntity {
    // 日志ID
    private Integer logId;
    // 用户ID
    private Integer userId;
    // 用户权限
    private Integer userType;
    // 登录时间
    private LocalDateTime loginDateTime;

    public LoginLogEntity() {
        super();
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public LocalDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(LocalDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

}
