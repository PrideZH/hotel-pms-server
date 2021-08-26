package com.pengfu.pms.entity;

import lombok.Data;

/**
 * @author PrideZH
 * @date 2021/7/22 - 17:43
 */
@Data
public class Room {

    private Long rid;

    private String roomNo;

    /** 房间状态 */
    private Integer status;

    /** 备注 */
    private String note;

    private RoomType roomType;

}
