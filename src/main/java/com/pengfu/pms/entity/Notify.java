package com.pengfu.pms.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author PrideZH
 * @date 2021/8/24 - 14:04
 */
@Data
public class Notify {

    private Long id;

    private Long senderId;

    private String title;

    private String content;

    private Timestamp createdTime;

}
