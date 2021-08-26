package com.pengfu.pms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pengfu.pms.entity.Notify;
import com.pengfu.pms.entity.NotifyUser;
import com.pengfu.pms.model.JsonParam;
import com.pengfu.pms.service.impl.MenuServiceImpl;
import com.pengfu.pms.service.impl.NotifyServiceImpl;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author PrideZH
 * @date 2021/8/24 - 14:09
 */
@RestController
@RequestMapping("/notify")
public class NotifyController {

    private NotifyServiceImpl notifyService;

    @Autowired
    public NotifyController(NotifyServiceImpl notifyService) {
        this.notifyService = notifyService;
    }

    @PostMapping("/")
    public JSONResult add(Notify notify) {
        return notifyService.add(notify);
    }

    @GetMapping("/list")
    public JSONResult getAllByid(@RequestParam Long id) {
        return notifyService.getAllByid(id);
    }

    @PostMapping("/update")
    public JSONResult set(@ModelAttribute JsonParam jsonParam) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        Notify notify = gson.fromJson(jsonParam.getParam(), Notify.class);
        return notifyService.set(notify);
    }

    @PostMapping("/user/update")
    public JSONResult setNotifyUser(@ModelAttribute JsonParam jsonParam) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        NotifyUser notifyUser = gson.fromJson(jsonParam.getParam(), NotifyUser.class);
        return notifyService.setNotifyUser(notifyUser);
    }

}
