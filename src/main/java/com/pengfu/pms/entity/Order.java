package com.pengfu.pms.entity;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/7/22 - 17:43
 */
@Data
public class Order {

    private Long oid;

    private String contactsName;

    private String contactsMobile;

    /** 入住日期 */
    private Date inDate;

    /** 入住天数 */
    private Integer days;

    private Room room;

    private List<Customer> customers;

    /** 备注 */
    private String orderNote;

    /** 押金 */
    private Double deposit;

    /** 总金额 */
    private Double totalAmount;

    /** 结账时间 */
    private Timestamp paymentTime;

    /** 支付方式 */
    private Integer payType;

    /** 支付状态 */
    private Integer payStatus;

    /** 创建时间 */
    private Timestamp createTime;

}
