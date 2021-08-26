package com.pengfu.pms.controller;
import com.pengfu.pms.entity.RoomType;
import com.pengfu.pms.service.impl.RoomTypeServiceImpl;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author PrideZH
 * @date 2021/8/19 - 21:49
 */
@RestController
@RequestMapping("/roomType")
public class RoomTypeController {

    private RoomTypeServiceImpl roomTypeService;

    @Autowired
    public RoomTypeController(RoomTypeServiceImpl roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @PostMapping("/")
    public JSONResult add(RoomType roomType) {
        return roomTypeService.add(roomType);
    }

    @DeleteMapping("/{rtid}")
    public JSONResult del(@PathVariable Long rtid) {
        return roomTypeService.del(rtid);
    }

    @GetMapping("/list")
    public JSONResult getAll() {
        return roomTypeService.getAll();
    }

}
