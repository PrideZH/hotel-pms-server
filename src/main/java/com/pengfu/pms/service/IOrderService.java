package com.pengfu.pms.service;

import com.pengfu.pms.entity.Order;
import com.pengfu.pms.util.JSONResult;

/**
 * @author PrideZH
 * @date 2021/8/16 - 15:06
 */
public interface IOrderService {

    Long add(Order order);

    JSONResult getAll(Integer page, Integer limit);

    void addJoin(Long oid, Long cid);

    JSONResult set(Order order);

}
