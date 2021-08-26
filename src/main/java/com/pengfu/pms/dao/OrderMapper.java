package com.pengfu.pms.dao;

import com.pengfu.pms.entity.Order;
import com.pengfu.pms.model.Turnover;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/16 - 15:05
 */
@Repository
public interface OrderMapper {

    Long insert(Order order);

    List<Order> select();

    Long update(Order order);

    Long insertOrderCustomer(Long oid, Long cid);

    Integer today();

    Integer total();

    List<Order> selectYear();
}
