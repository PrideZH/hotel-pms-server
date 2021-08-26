package com.pengfu.pms.controller;

import com.pengfu.pms.service.impl.StatisticalServiceImpl;
import com.pengfu.pms.service.impl.UserServiceImpl;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PrideZH
 * @date 2021/8/25 - 12:48
 */
@RestController
@RequestMapping("/statistical")
public class StatisticalController {

    private StatisticalServiceImpl statisticalService;

    @Autowired
    public StatisticalController(StatisticalServiceImpl statisticalService) {
        this.statisticalService = statisticalService;
    }

    @GetMapping("/")
    public JSONResult get() {
        return statisticalService.get();
    }


    @GetMapping("/turnover")
    public JSONResult getTurnover() {
        return statisticalService.getTurnover();
    }

}
