package com.pengfu.pms.controller;

import com.google.gson.Gson;
import com.pengfu.pms.entity.Room;
import com.pengfu.pms.entity.User;
import com.pengfu.pms.model.JsonParam;
import com.pengfu.pms.service.impl.UserServiceImpl;
import com.pengfu.pms.util.JSONResult;
import com.pengfu.pms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/7/22 - 17:52
 */
@RestController
@RequestMapping("/auth")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public JSONResult login(@RequestParam("email") String email, @RequestParam("pwd") String pwd) {
        return userService.login(email, pwd);
    }

    @PostMapping("/register")
    public JSONResult register(@ModelAttribute User user) {
        return userService.register(user);
    }

    @DeleteMapping("/{id}")
    public JSONResult del(@PathVariable Long id) {
        return userService.del(id);
    }

    /**
     * 获取用户登录信息
     * @param token 令牌
     * @return 用户登录信息
     */
    @PostMapping("/getUserOfLogin")
    public JSONResult getUser(@RequestHeader String token) {
        return userService.getUserOfLogin(token);
    }

    /**
     * 返回指定id的用户
     * @return 用户详情数据
     */
    @GetMapping("/{id}")
    public JSONResult queryUser(@PathVariable Long id) {
        User user = userService.queryById(id);
        return JSONResult.ok(user);
    }

    // @RequiresPermissions("user:list")
    @GetMapping("/list")
    public JSONResult getAll(@RequestParam(value="page", defaultValue="1") Integer page,
                             @RequestParam(value="limit", defaultValue="10") Integer limit) {
        return userService.queryAll(page, limit);
    }

    /**
     * 获取用户信息
     * @param token 令牌
     * @return 用户信息
     */
    @PostMapping("/me")
    public JSONResult getMe(@RequestHeader String token) {
        return userService.getUserByToken(token);
    }

    @PostMapping("/pwd/update")
    public JSONResult setPwd(@RequestParam Long uid, @RequestParam String pwd) {
        User user = new User();
        user.setId(uid);
        user.setPwd(pwd);
        return userService.set(user);
    }

    @PostMapping("/role/update")
    public JSONResult setRole(@RequestParam Long uid, @RequestParam String rids) {
        List<Long> ridList = StringUtil.toLongArray(rids, ",");
        return userService.setRole(uid, ridList);
    }

    @PostMapping("/portrait/update")
    public JSONResult setRole(@ModelAttribute JsonParam jsonParam) {
        User user = new Gson().fromJson(jsonParam.getParam(), User.class);
        String base64 = String.valueOf(user.getPortrait()).split(",")[1];
        try {
            byte[] head = new BASE64Decoder().decodeBuffer(base64);
            user.setPortrait(head);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userService.setPortrait(user);
    }

}
