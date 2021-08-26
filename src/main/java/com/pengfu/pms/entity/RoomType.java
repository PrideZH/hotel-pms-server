package com.pengfu.pms.entity;

import lombok.Data;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/7/22 - 17:43
 */
@Data
public class RoomType {

    private Integer rtid;

    private String name;

    /** 可住人数 */
    private Integer number;

    private Integer area;

    /** 租金 */
    private Double rent;

}
