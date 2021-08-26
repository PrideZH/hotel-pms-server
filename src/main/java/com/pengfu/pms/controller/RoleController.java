package com.pengfu.pms.controller;

import com.google.gson.Gson;
import com.pengfu.pms.entity.Customer;
import com.pengfu.pms.entity.Role;
import com.pengfu.pms.entity.Room;
import com.pengfu.pms.model.JsonParam;
import com.pengfu.pms.service.impl.OrderServiceImpl;
import com.pengfu.pms.service.impl.RoleServiceImpl;
import com.pengfu.pms.util.JSONResult;
import com.pengfu.pms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/17 - 15:46
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public JSONResult add(@ModelAttribute JsonParam jsonParam) {
        Role role = new Gson().fromJson(jsonParam.getParam(), Role.class);
        roleService.add(role);
        return roleService.addJoinMenu(role.getRid(), role.getMenus());
    }

    @DeleteMapping("/{id}")
    public JSONResult del(@PathVariable Long id) {
        return roleService.del(id);
    }

    @GetMapping("/list")
    public JSONResult getAll() {
        return roleService.getAll();
    }

    @PostMapping("/menu/update")
    public JSONResult setRole(@ModelAttribute JsonParam jsonParam) {
        Role role = new Gson().fromJson(jsonParam.getParam(), Role.class);
        return roleService.setRole(role.getRid(), role.getMenus());
    }

}
