package com.accenture.powerup.bookmng.controller;

import com.accenture.powerup.bookmng.exception.BusinessFailureException;
import com.accenture.powerup.bookmng.requestdto.EditUserForm;
import com.accenture.powerup.bookmng.requestdto.RegistrationForm;
import com.accenture.powerup.bookmng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户接口处理器
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册。
     * <p>根据用户输入的信息注册用户</p>
     *
     * @param form   注册表单。
     * @param errors 参数校验错误信息
     * @return
     */
    @PostMapping("/registration")
    public int registerUser(@RequestBody @Valid RegistrationForm form, Errors errors){
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return userService.userRegister(form);
    }

    /**
     * 用户信息修改。
     * <p>根据用户输入的信息修改用户信息</p>
     *
     * @param form   表单。
     * @param errors 参数校验错误信息
     * @return
     */
    @PostMapping("/edit")
    public int editUser(@RequestBody @Valid EditUserForm form, Errors errors){
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return userService.editUser(form);
    }


}
