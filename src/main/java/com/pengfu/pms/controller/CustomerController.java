package com.pengfu.pms.controller;

import com.google.gson.Gson;
import com.pengfu.pms.entity.Customer;
import com.pengfu.pms.entity.Room;
import com.pengfu.pms.model.JsonParam;
import com.pengfu.pms.service.impl.CustomerServiceImpl;
import com.pengfu.pms.util.JSONResult;
import com.pengfu.pms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/16 - 12:19
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * 查找用户是否存在，存在获取，不存在插入
     * @param customer 用户信息
     * @return 用户信息
     */
    @PostMapping("/over")
    public JSONResult addOver(@ModelAttribute Customer customer) {
        Customer res = customerService.addOver(customer);
        if (res == null) {
            JSONResult.errorMessage("添加失败");
        }
        return JSONResult.ok(res);
    }

    @DeleteMapping("/{id}")
    public JSONResult del(@PathVariable Long id) {
        return customerService.del(id);
    }

    @GetMapping("/idCard")
    public JSONResult getByIdCard(@RequestParam String idCard) {
        return customerService.findByIdCard(idCard);
    }

    @GetMapping("/list")
    public JSONResult getAll(@RequestParam(value="page", defaultValue="1") Integer page,
                             @RequestParam(value="limit", defaultValue="10") Integer limit) {
        return customerService.getAll(page, limit);
    }

    @PostMapping("/update")
    public JSONResult set(@ModelAttribute JsonParam jsonParam) {
        Customer customer = new Gson().fromJson(jsonParam.getParam(), Customer.class);
        return customerService.set(customer);
    }

}
