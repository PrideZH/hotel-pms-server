package com.pengfu.pms.service;

import com.pengfu.pms.entity.Menu;
import com.pengfu.pms.entity.Role;
import com.pengfu.pms.util.JSONResult;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/17 - 15:50
 */
public interface IRoleService {

    JSONResult add(Role role);

    JSONResult addJoinMenu(Long rid, List<Menu> menus);

    JSONResult getAll();

    JSONResult del(Long id);

    JSONResult setRole(Long rid, List<Menu> menus);
}
