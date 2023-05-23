package com.accenture.powerup.bookmng.requestdto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * 注册入力参数
 */
public class RegistrationForm {
    @NotBlank(message = "用户名不能为空。")
    private String userName;
    @NotBlank(message = "密码不能为空。")
    private String password;
    @NotBlank(message = "确认密码不能为空。")
    private String confirmPassword;
    private String email;
    private LocalDate birthday;
    private Integer gender;
    private Integer grade;
    private String Interest;
    private String introduction;

    public RegistrationForm() {
    }

    public RegistrationForm(String userName, String password, String confirmPassword, String email, LocalDate birthday, Integer gender, Integer grade, String interest, String introduction) {
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
