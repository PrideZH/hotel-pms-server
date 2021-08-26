package com.pengfu.pms.dao;

import com.pengfu.pms.entity.RoomType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/19 - 21:51
 */
@Repository
public interface RoomTypeMapper {

    int insert(RoomType roomType);

    int deleteById(Long rtid);

    List<RoomType> select();
}
