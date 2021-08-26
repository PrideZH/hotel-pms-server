package com.pengfu.pms.controller;

import com.google.gson.Gson;
import com.pengfu.pms.entity.Room;
import com.pengfu.pms.model.JsonParam;
import com.pengfu.pms.service.impl.RoomServiceImpl;
import com.pengfu.pms.util.JSONResult;
import com.pengfu.pms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * @author PrideZH
 * @date 2021/8/15 - 15:03
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    private RoomServiceImpl roomService;

    @Autowired
    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/list")
    public JSONResult getAll(@RequestParam(value="page", defaultValue="0") Integer page,
                             @RequestParam(value="limit", defaultValue="0") Integer limit,
                             @RequestParam(required=false) Integer minRent,
                             @RequestParam(required=false) Integer maxRent,
                             @RequestParam(required=false) String name,
                             @RequestParam(required=false) Integer number,
                             @RequestParam(required=false) Integer status,
                             @RequestParam(required=false) String inDate,
                             @RequestParam(required=false) Integer days) throws ParseException {
        Date date = null;
        if (inDate != null) {
            inDate = inDate.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            date = new Date(format.parse(inDate).getTime());
        }
        return roomService.queryAll(page, limit, minRent, maxRent, name, number, status, date, days);
    }

    @PostMapping("/")
    public JSONResult add(@ModelAttribute JsonParam jsonParam) {
//        if (commonUtil.hasNotExist(room, "name", "")) {
//            return JSONResult.errorMessage("缺少参数");
//        }
        Room room = new Gson().fromJson(jsonParam.getParam(), Room.class);
        String[] roomNos = room.getRoomNo().split(",");
        Room[] rooms = new Room[roomNos.length];
        for (int i = 0; i < roomNos.length; i++) {
            rooms[i] = new Room();
            rooms[i].setRoomNo(roomNos[i]);
            rooms[i].setNote(room.getNote());
            rooms[i].setRoomType(room.getRoomType());
            rooms[i].setStatus(room.getStatus());
        }
        return roomService.addList(rooms);
    }

    @PostMapping("/list")
    public JSONResult addList(@ModelAttribute JsonParam jsonParam) {
        Room[] rooms = new Gson().fromJson(jsonParam.getParam(), Room[].class);
        return roomService.addList(rooms);
    }

    @DeleteMapping("/{id}")
    public JSONResult del(@PathVariable Long id) {
        return roomService.del(id);
    }

    @PostMapping("/update")
    public JSONResult set(@ModelAttribute JsonParam jsonParam) {
        Room room = new Gson().fromJson(jsonParam.getParam(), Room.class);
        return roomService.set(room);
    }

}
