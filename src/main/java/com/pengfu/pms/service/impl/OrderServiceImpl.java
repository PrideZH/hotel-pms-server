package com.pengfu.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengfu.pms.constant.STATUS;
import com.pengfu.pms.dao.OrderMapper;
import com.pengfu.pms.entity.Order;
import com.pengfu.pms.service.IOrderService;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/16 - 15:06
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Long add(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public JSONResult getAll(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Order> orders = orderMapper.select();
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        return JSONResult.ok(pageInfo);
    }

    @Override
    public void addJoin(Long oid, Long cid) {
        orderMapper.insertOrderCustomer(oid, cid);
    }

    @Override
    public JSONResult set(Order order) {
        order.setPaymentTime(null);
        if (order.getPayStatus() == STATUS.PAID.ordinal()) {
            order.setPaymentTime(new Timestamp(System.currentTimeMillis()));
        }
        if (orderMapper.update(order) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("修改失败");
    }

}
