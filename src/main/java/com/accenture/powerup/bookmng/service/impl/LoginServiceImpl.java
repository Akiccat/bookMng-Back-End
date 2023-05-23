package com.accenture.powerup.bookmng.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.accenture.powerup.bookmng.entity.GetLoginLogEntity;
import com.accenture.powerup.bookmng.entity.LoginLogEntity;
import com.accenture.powerup.bookmng.entity.UserEntity;
import com.accenture.powerup.bookmng.exception.BusinessFailureException;
import com.accenture.powerup.bookmng.repository.LoginLogRepository;
import com.accenture.powerup.bookmng.repository.UserRepository;
import com.accenture.powerup.bookmng.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 登录业务实现层。
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginLogRepository loginLogRepository;

    /**
     * 用户登录。
     * <p>根据用户名、密码登录系统</p>
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Override
    public UserEntity login(String userName, String password) {
        // 根据用户名、密码查询用户信息。
        UserEntity user = null;
        try {
            user = userRepository.selectUserByNameAndPass(userName, password);
            LocalDateTime now = LocalDateTime.now();
            if (user == null || now.isBefore(user.getValidStartTime()) || now.isAfter(user.getValidEndTime())) {
                // 用户不存在，或不在有效期内，则抛出业务异常。
                throw new BusinessFailureException(true);
            }
            LoginLogEntity loginLog = new LoginLogEntity();
            loginLog.setUserId(user.getUserId());
            loginLog.setUserType(user.getUserType());
            loginLog.setLoginDateTime(now);
            // 新建登录日志。
            loginLogRepository.insert(loginLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 用户登录日志。
     * <p>查询用户登录日志</p>
     *
     * @return 用户信息
     */
    @Override
    public List<GetLoginLogEntity> getLogs() {
        List<GetLoginLogEntity> list = null;
        try {
            list = loginLogRepository.getLogs();
            for (GetLoginLogEntity entity : list) {
                entity.setUserName(userRepository.getUser(entity.getUserId()).getUserName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
