package com.pengfu.pms.service;

import com.pengfu.pms.entity.Room;
import com.pengfu.pms.entity.RoomType;
import com.pengfu.pms.util.JSONResult;

import java.sql.Date;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/15 - 14:59
 */
public interface IRoomService {

    JSONResult queryAll(Integer page, Integer limit,
                        Integer minRent, Integer maxRent, String name, Integer number, Integer status,
                        Date inDate, Integer days);

    JSONResult add(Room room);

    JSONResult addList(Room[] rooms);

    Room get(Long id);

    JSONResult del(Long id);

    JSONResult set(Room room);
}
