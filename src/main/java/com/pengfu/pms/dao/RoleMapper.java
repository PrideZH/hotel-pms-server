package com.pengfu.pms.dao;

import com.pengfu.pms.entity.Menu;
import com.pengfu.pms.entity.Role;
import com.pengfu.pms.util.JSONResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/7/22 - 19:07
 */
@Repository
public interface RoleMapper {

    Long insert(Role role);

    Long deleteById(Long rid);

    List<Role> select();

    Long deleteMenus(Long rid);

    Long insertJoinMenu(Long rid, List<Menu> menus);
}
