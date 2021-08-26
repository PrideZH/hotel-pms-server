package com.pengfu.pms.controller;

import com.pengfu.pms.service.impl.MenuServiceImpl;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PrideZH
 * @date 2021/8/19 - 15:04
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private MenuServiceImpl menuService;

    @Autowired
    public MenuController(MenuServiceImpl menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public JSONResult getAll() {
        return menuService.getAll();
    }

}
