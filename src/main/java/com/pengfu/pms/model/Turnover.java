package com.pengfu.pms.model;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/25 - 13:29
 */
@Data
public class Turnover {

    /** 最近7日收益 */
    private Double[] revenues;

    private Double yearAmount;

    private Double monthAmount;

    private Double dayAmount;

    /** 年增长率 */
    private Double daily;

    /** 年增长率 */
    private Double monthly;

    /** 年增长率 */
    private Double yearly;

}
