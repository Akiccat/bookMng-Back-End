package com.accenture.powerup.bookmng.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

/**
 * 用户信息实体类。
 */
@Alias("User")
public class UserEntity extends BaseEntity {
    // 用户ID
    private Integer userId;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 用户类型
    private Integer userType;
    // email
    private String email;
    // 生日
    private LocalDate birthday;
    // 性别
    private Integer gender;
    // 年级
    private Integer grade;
    // 兴趣
    private String interest;
    // 自我介绍
    private String introduction;
    // 有效期开始时间
    private LocalDateTime validStartTime;
    // 有效期结束时间
    private LocalDateTime validEndTime;

    public UserEntity() {
        super();
    }

    public UserEntity(Integer userId, String userName, String password, String email, LocalDate birthday, Integer gender, Integer grade, String interest, String introduction) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.grade = grade;
        this.interest = interest;
        this.introduction = introduction;
    }

    public UserEntity(Integer userId, String userName) {
      super();
      this.userId = userId;
      this.userName = userName;
  }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public LocalDateTime getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(LocalDateTime validStartTime) {
        this.validStartTime = validStartTime;
    }

    public LocalDateTime getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(LocalDateTime validEndTime) {
        this.validEndTime = validEndTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", grade=" + grade +
                ", interest='" + interest + '\'' +
                ", introduction='" + introduction + '\'' +
                ", validStartTime=" + validStartTime +
                ", validEndTime=" + validEndTime +
                '}';
    }
}
