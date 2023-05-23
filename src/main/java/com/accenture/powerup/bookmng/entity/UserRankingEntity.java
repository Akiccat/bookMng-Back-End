package com.accenture.powerup.bookmng.entity;

import org.apache.ibatis.type.Alias;
/**
 * 用户排行实体类。
 */
@Alias("UserRanking")
public class UserRankingEntity extends RankingEntity{
    String userName;
    Integer userId;
    Integer borrowCount;

    public UserRankingEntity() {
    }

    public UserRankingEntity(String userName, Integer userId, Integer borrowCount) {
        this.userName = userName;
        this.userId = userId;
        this.borrowCount = borrowCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    @Override
    public String toString() {
        return "{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", borrowCount=" + borrowCount +
                '}';
    }
}
