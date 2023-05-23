package com.accenture.powerup.bookmng.controller;

import javax.validation.Valid;

import com.accenture.powerup.bookmng.entity.GetLoginLogEntity;
import com.accenture.powerup.bookmng.entity.LoginLogEntity;
import com.accenture.powerup.bookmng.entity.UserEntity;
import com.accenture.powerup.bookmng.exception.BusinessFailureException;
import com.accenture.powerup.bookmng.requestdto.LoginForm;
import com.accenture.powerup.bookmng.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录接口处理器。
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录。
     * <p>根据用户名、密码登录系统</p>
     * 
     * @param form 用户名，密码。
     * @param errors 参数校验错误信息
     * @return 用户信息。
     */
    @PostMapping("/login")
    public UserEntity login(@RequestBody @Valid LoginForm form, Errors errors) {
        System.out.println(errors);
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return loginService.login(form.getUserName(), form.getPassword());
    }

    /**
     * 获取登陆日志。
     * <p>获取全部用户登陆日志</p>
     *
     * @return 日志列表。
     */
    @PostMapping("/getLogs")
    public List<GetLoginLogEntity> getLogs(){
        return loginService.getLogs();
    }
}
