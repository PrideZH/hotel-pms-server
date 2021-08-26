package com.pengfu.pms.service.impl;

import com.pengfu.pms.dao.RoomTypeMapper;
import com.pengfu.pms.entity.RoomType;
import com.pengfu.pms.service.IRoomTypeService;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author PrideZH
 * @date 2021/8/19 - 21:50
 */
@Service
public class RoomTypeServiceImpl implements IRoomTypeService {

    private RoomTypeMapper roomTypeMapper;

    @Autowired
    public RoomTypeServiceImpl(RoomTypeMapper roomTypeMapper) {
        this.roomTypeMapper = roomTypeMapper;
    }

    @Override
    public JSONResult add(RoomType roomType) {
        if (roomTypeMapper.insert(roomType) > 0) {
            return JSONResult.ok(roomType);
        }
        return JSONResult.errorMessage("添加失败");
    }

    @Override
    public JSONResult del(Long rtid) {
        if (roomTypeMapper.deleteById(rtid) > 0) {
            return JSONResult.ok(rtid);
        }
        return JSONResult.errorMessage("删除失败");
    }

    @Override
    public JSONResult getAll() {
        return JSONResult.ok(roomTypeMapper.select());
    }
}
