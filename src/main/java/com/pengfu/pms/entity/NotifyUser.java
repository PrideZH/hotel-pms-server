package com.pengfu.pms.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author PrideZH
 * @date 2021/8/24 - 14:07
 */
@Data
public class NotifyUser {

    private long id;

    private Notify notify;

    private Long recipientId;

    private Long state;

    private Timestamp readTime;

    private Timestamp createdTime;

}
