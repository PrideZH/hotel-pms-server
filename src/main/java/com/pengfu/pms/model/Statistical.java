package com.pengfu.pms.model;

import lombok.Data;

/**
 * @author PrideZH
 * @date 2021/8/25 - 12:45
 */
@Data
public class Statistical {

    /** 今日订单数 */
    private Integer todayOrder;

    /** 总订单数 */
    private Integer totalOrder;

    /** 今日顾客数 */
    private Integer todayCustomer;

    /** 总顾客数 */
    private Integer totalCustomer;

}
