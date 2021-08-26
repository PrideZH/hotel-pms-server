package com.pengfu.pms.service.impl;

import com.github.pagehelper.PageInfo;
import com.pengfu.pms.dao.MenuMapper;
import com.pengfu.pms.entity.Menu;
import com.pengfu.pms.service.IMenuService;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/19 - 15:07
 */
@Service
public class MenuServiceImpl implements IMenuService {

    private MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public JSONResult getAll() {
        List<Menu> menus = menuMapper.select();
        return JSONResult.ok(menus);
    }
}
