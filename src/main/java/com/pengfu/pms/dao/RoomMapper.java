package com.pengfu.pms.dao;

import com.pengfu.pms.entity.Room;
import com.pengfu.pms.entity.RoomType;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/15 - 14:57
 */
@Repository
public interface RoomMapper {

    Long insert(Room room);

    Long insertList(Room[] rooms);

    Long delete(Long rid);

    /**
     * 条件查询
     * @param minRent 最小租金 (包含)
     * @param maxRent 最大租金 (包含)
     * @param name 房间类型名
     * @param number 房间可住人数
     * @return 房间信息列表
     */
    List<Room> selectRoom(Integer minRent, Integer maxRent, String name, Integer number, Integer status,
                          Date inDate, Integer days);

    Room selectById(Long rid);

    Long update(Room room);
}
