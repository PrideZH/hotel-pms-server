package com.pengfu.pms.controller;

import com.google.gson.*;
import com.pengfu.pms.entity.Customer;
import com.pengfu.pms.entity.Order;
import com.pengfu.pms.entity.Room;
import com.pengfu.pms.model.JsonParam;
import com.pengfu.pms.service.impl.CustomerServiceImpl;
import com.pengfu.pms.service.impl.OrderServiceImpl;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.sql.Date;

/**
 * @author PrideZH
 * @date 2021/8/16 - 15:06
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderServiceImpl orderService;
    private CustomerServiceImpl customerService;

    @Autowired
    public OrderController(OrderServiceImpl orderService, CustomerServiceImpl customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostMapping("/")
    @Transactional(rollbackFor=Exception.class)
    public JSONResult add(@ModelAttribute JsonParam jsonParam) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Order order = gson.fromJson(jsonParam.getParam(), Order.class);
        if (orderService.add(order) == 0) {
            return JSONResult.errorMessage("订单信息错误");
        }
        for (Customer customer : order.getCustomers()) {
            customer = customerService.addOver(customer);
            orderService.addJoin(order.getOid(), customer.getCid());
        }
        return JSONResult.ok(order);
    }

    @GetMapping("/list")
    public JSONResult getAll(@RequestParam(value="page", defaultValue="1") Integer page,
                             @RequestParam(value="limit", defaultValue="10") Integer limit) {
        return orderService.getAll(page, limit);
    }

    @PostMapping("/update")
    public JSONResult set(@ModelAttribute JsonParam jsonParam) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Order order = gson.fromJson(jsonParam.getParam(), Order.class);
        return orderService.set(order);
    }

}
