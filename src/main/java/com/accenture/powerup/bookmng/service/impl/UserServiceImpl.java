package com.accenture.powerup.bookmng.service.impl;

import com.accenture.powerup.bookmng.entity.UserEntity;
import com.accenture.powerup.bookmng.exception.BusinessFailureException;
import com.accenture.powerup.bookmng.repository.UserRepository;
import com.accenture.powerup.bookmng.requestdto.EditBookForm;
import com.accenture.powerup.bookmng.requestdto.EditUserForm;
import com.accenture.powerup.bookmng.requestdto.RegistrationForm;
import com.accenture.powerup.bookmng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户业务实现层
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 用户注册。
     * <p>根据用户输入的信息注册用户</p>
     *
     * @param form 用户的全部信息
     */
    @Override
    public int userRegister(RegistrationForm form) {
        try {
            UserEntity registerUser = new UserEntity();
            registerUser.setUserName(form.getUserName());
            registerUser.setPassword(form.getPassword());
            registerUser.setUserType(2);
            registerUser.setEmail(form.getEmail());
            //转化日期格式
            DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String birthdayString = form.getBirthday().format(birthdayFormatter);
            LocalDate birthday = LocalDate.parse(birthdayString,birthdayFormatter);

            registerUser.setBirthday(birthday);
            registerUser.setGender(form.getGender());
            registerUser.setGrade(form.getGrade());
            registerUser.setInterest(form.getInterest());
            registerUser.setIntroduction(form.getIntroduction());

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime endYear = now.plusYears(1);

            registerUser.setValidEndTime(endYear);
            return userRepository.registerUser(registerUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 用户修改。
     * <p>根据用户输入的信息修改用户</p>
     *
     * @param form 用户的全部信息
     */
    @Override
    public int editUser(EditUserForm form) {
        try {
            UserEntity edit = new UserEntity(
                    form.getUserId(),
                    form.getUserName(),
                    form.getPassword(),
                    form.getEmail(),
                    form.getBirthday(),
                    form.getGender(),
                    form.getGrade(),
                    form.getInterest(),
                    form.getIntroduction()
            );
            return userRepository.editUser(edit);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
