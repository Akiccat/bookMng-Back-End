package com.accenture.powerup.bookmng.requestdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 修改用户信息入力参数
 */
public class EditUserForm {
    @NotNull(message = "用户ID不能为空。")
    private Integer userId;
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
    private LocalDate birthday;
    private Integer gender;
    private Integer grade;
    private String Interest;
    private String introduction;

    public EditUserForm() {
    }

    public EditUserForm(Integer userId, String userName, String password, String confirmPassword, String email, LocalDate birthday, Integer gender, Integer grade, String interest, String introduction) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.grade = grade;
        Interest = interest;
        this.introduction = introduction;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
        return Interest;
    }

    public void setInterest(String interest) {
        Interest = interest;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
