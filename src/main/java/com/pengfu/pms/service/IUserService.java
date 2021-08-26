package com.pengfu.pms.service;

import com.pengfu.pms.entity.User;
import com.pengfu.pms.util.JSONResult;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/7/19 - 20:27
 */
public interface IUserService {

    /**
     * 判断Token是否有效
     * @param token Token令牌
     * @return 是否有效
     */
    Boolean isValidToken(String token);

    /**
     * 重置Token时间
     * @param token Token令牌
     */
    void updateToken(String token);

    /**
     * 获取指定 ID 用户
     * @param id 用户 ID
     * @return 用户实例
     */
    User queryById(Long id);

    /**
     * 获取指定登录信息用户
     * @param username 用户账号
     * @param password 用户密码
     * @return 请求结果
     */
    JSONResult login(String username, String password);

    /**
     * 用户注册
     * @param user 用户信息
     * @return 请求结果
     */
    JSONResult register(User user);

    /**
     * 获取用户登录信息
     * @param token 令牌
     * @return 用户信息
     */
    JSONResult getUserOfLogin(String token);

    JSONResult del(Long id);

    JSONResult queryAll(Integer page, Integer limit);

    JSONResult getUserByToken(String token);

    JSONResult set(User user);

    JSONResult setRole(Long uid, List<Long> rids);

    JSONResult setPortrait(User user);
}
