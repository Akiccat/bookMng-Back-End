package com.accenture.powerup.bookmng.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.accenture.powerup.bookmng.entity.UserEntity;

/**
 * 用户Mapper映射器。
 */
@Repository
public interface UserRepository {
    /**
     * 根据用户名密码查询用户。
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    public UserEntity selectUserByNameAndPass(@Param("userName")String userName, @Param("password")String password);

    /**
     * 用户注册。
     * <p>根据用户输入的信息注册用户</p>
     *
     * @param user 用户的全部信息
     * @return
     */
    public int registerUser(UserEntity user);

    /**
     * 用户查找。
     * <p>根据用户id查找用户</p>
     *
     * @param userId 用户id
     */
    UserEntity getUser(Integer userId);

    /**
     * 用户查找。
     * <p>根据用户id查找用户</p>
     *
     * @param edit 修改后的数据
     * @return
     */
    int editUser(UserEntity edit);
}
