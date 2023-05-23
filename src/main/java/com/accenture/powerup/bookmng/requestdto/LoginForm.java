package com.accenture.powerup.bookmng.requestdto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * 登录入力参数。
 */
public class LoginForm {
    // 用户名
    @NotBlank(message = "用户名不能为空。")
    private String userName;
    // 密码
    @NotBlank(message = "密码不能为空。")
    private String password;

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
}
