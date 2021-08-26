package com.pengfu.pms.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/7/19 - 20:40
 */
@Data
public class User {

    private Long id;

    private String email;

    private String pwd;

    private String name;

    /** 头像 */
    private Object portrait;

    /** 电话 */
    private String mob;

    private String token;

    private Timestamp tokenExp;

    private List<Role> roles = new ArrayList<>();

}
