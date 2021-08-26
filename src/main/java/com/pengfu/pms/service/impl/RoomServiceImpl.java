package com.pengfu.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengfu.pms.dao.RoomMapper;
import com.pengfu.pms.entity.Room;
import com.pengfu.pms.service.IRoomService;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.sql.Date;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/15 - 14:59
 */
@Service
public class RoomServiceImpl implements IRoomService {

    private RoomMapper roomMapper;

    @Autowired
    public RoomServiceImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public JSONResult queryAll(Integer page, Integer limit,
                               Integer minRent, Integer maxRent, String name, Integer number, Integer status,
                               Date inDate, Integer days) {
        if (page == 0 || limit == 0) {
            List<Room> rooms = roomMapper.selectRoom(minRent, maxRent, name, number, status, inDate, days);
            return JSONResult.ok(rooms);
        } else {
            PageHelper.startPage(page, limit);
            List<Room> rooms = roomMapper.selectRoom(minRent, maxRent, name, number, status, inDate, days);
            PageInfo<Room> pageInfo = new PageInfo<>(rooms);
            return JSONResult.ok(pageInfo);
        }
    }

    @Override
    public JSONResult add(Room room) {
        if (roomMapper.insert(room) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("添加失败");
    }

    @Override
    public JSONResult addList(Room[] rooms) {
        if (roomMapper.insertList(rooms) > 0) {
            return JSONResult.ok(rooms);
        }
        return JSONResult.errorMessage("添加失败");
    }

    @Override
    public Room get(Long id) {
        return roomMapper.selectById(id);
    }

    @Override
    public JSONResult del(Long id) {
        if (roomMapper.delete(id) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("删除失败");
    }

    @Override
    public JSONResult set(Room room) {
        if (roomMapper.update(room) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("修改失败");
    }

}
