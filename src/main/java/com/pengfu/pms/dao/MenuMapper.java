package com.pengfu.pms.dao;

import com.pengfu.pms.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/15 - 12:03
 */
@Repository
public interface MenuMapper {

    List<Menu> select();

}
