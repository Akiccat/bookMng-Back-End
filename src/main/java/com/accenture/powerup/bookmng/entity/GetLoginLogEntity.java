package com.accenture.powerup.bookmng.entity;

import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
/**
 * 获取登录日志实体类。
 */
@Alias("GetLoginLog")
public class GetLoginLogEntity {
    // 用户ID
    private Integer userId;
    // 用户权限
    private Integer userType;
    // 登录时间
    private LocalDateTime loginDateTime;
    private String userName;

    public GetLoginLogEntity() {
    }

    public GetLoginLogEntity(Integer userId, Integer userType, LocalDateTime loginDateTime, String userName) {
        this.userId = userId;
        this.userType = userType;
        this.loginDateTime = loginDateTime;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "GetLoginLogEntity{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", loginDateTime=" + loginDateTime +
                ", userName='" + userName + '\'' +
                '}';
    }
}
