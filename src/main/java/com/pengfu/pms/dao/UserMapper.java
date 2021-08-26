package com.pengfu.pms.dao;

import com.pengfu.pms.entity.Role;
import com.pengfu.pms.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/7/19 - 20:39
 */
@Repository
public interface UserMapper {

    /**
     * 获取所有用户信息
     * @return 用户列表
     */
    List<User> selectAll();

    /**
     * 通过 ID 查询用户
     * @param id 用户 ID
     * @return 用户实例
     */
    User selectById(Long id);

    /**
     * 通过用户账号查询用户
     * @param email 用户账号
     * @return 用户实例
     */
    User selectByEmail(String email);

    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 插入成功数量
     */
    int insert(User user);

    Long deleteByUid(Long id);

    /**
     * 更新令牌
     * @param id 用户ID
     * @param token 令牌
     * @param tokenExp 有效期
     */
    void updateToken(Long id, String token, Timestamp tokenExp);

    /**
     * 通过令牌获取用户信息
     * @param token 令牌
     * @return 用户信息
     */
    User selectByToken(String token);

    /**
     * 获取令牌有效性
     * @param token 令牌
     * @return 有效时间
     */
    User selectToken(String token);

    Long insertRole(Long uid, List<Long> rids);

    Long deleteRoleByUid(Long uid);

    List<Role> selectRoleByUid(Long uid);

    Long updatePortrait(User user);

    Long update(User user);
}
