package com.pengfu.pms.service.impl;

import com.github.pagehelper.PageInfo;
import com.pengfu.pms.dao.OrderMapper;
import com.pengfu.pms.dao.RoleMapper;
import com.pengfu.pms.entity.Menu;
import com.pengfu.pms.entity.Role;
import com.pengfu.pms.service.IRoleService;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/17 - 15:50
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public JSONResult add(Role role) {
        if (roleMapper.insert(role) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("添加失败");
    }

    @Override
    public JSONResult addJoinMenu(Long rid, List<Menu> menus) {
        if (roleMapper.insertJoinMenu(rid, menus) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("授权失败");
    }

    @Override
    public JSONResult del(Long id) {
        if (roleMapper.deleteById(id) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("删除失败");
    }

    @Override
    public JSONResult setRole(Long rid, List<Menu> menus) {
        roleMapper.deleteMenus(rid);
        roleMapper.insertJoinMenu(rid, menus);
        return JSONResult.ok("修改成功");
    }

    @Override
    public JSONResult getAll() {
        List<Role> roles = roleMapper.select();
        return JSONResult.ok(roles);
    }

}
