package com.accenture.powerup.bookmng.service;

import com.accenture.powerup.bookmng.entity.UserEntity;
import com.accenture.powerup.bookmng.requestdto.EditBookForm;
import com.accenture.powerup.bookmng.requestdto.EditUserForm;
import com.accenture.powerup.bookmng.requestdto.RegistrationForm;

import javax.validation.Valid;

/**
 * 用户业务接口
 */
public interface UserService {

    /**
     * 用户注册。
     * <p>根据用户输入的信息注册用户</p>
     *
     * @param user 用户的全部信息
     */
    public int userRegister(RegistrationForm user);

    /**
     * 用户修改。
     * <p>根据用户输入的信息修改用户信息</p>
     *
     * @param form 用户的全部信息
     */
    int editUser(EditUserForm form);
}
