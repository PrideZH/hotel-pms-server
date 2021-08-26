package com.pengfu.pms.entity;

import lombok.Data;

/**
 * @author PrideZH
 * @date 2021/7/22 - 17:48
 */
@Data
public class Customer {

    private Long cid;

    private String name;

    private Integer age;

    private Integer gender;

    private String idCard;

    private String mobile;

    private Integer level;

}
