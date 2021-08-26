package com.pengfu.pms.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/7/22 - 19:06
 */
@Data
public class Role {

    private Long rid;

    private String nameZh;

    private List<Menu> menus = new ArrayList<>();

}
