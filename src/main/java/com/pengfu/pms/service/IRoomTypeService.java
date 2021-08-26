package com.pengfu.pms.service;

import com.pengfu.pms.entity.RoomType;
import com.pengfu.pms.util.JSONResult;

/**
 * @author PrideZH
 * @date 2021/8/19 - 21:50
 */
public interface IRoomTypeService {

    JSONResult getAll();

    JSONResult del(Long rtid);

    JSONResult add(RoomType roomType);
}
