package com.pengfu.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengfu.pms.dao.MenuMapper;
import com.pengfu.pms.dao.UserMapper;
import com.pengfu.pms.entity.Menu;
import com.pengfu.pms.entity.Role;
import com.pengfu.pms.entity.User;
import com.pengfu.pms.service.IUserService;
import com.pengfu.pms.util.JSONResult;
import com.pengfu.pms.util.MD5Util;
import com.pengfu.pms.util.ResultCode;
import com.pengfu.pms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @author PrideZH
 * @date 2021/7/19 - 20:28
 */
@Service
public class UserServiceImpl implements IUserService {

    /**
     * 令牌有效期
     */
    private final long TOKEN_EXP = 1000 * 60 * 60 * 12;

    private UserMapper userMapper;
    private MenuMapper menuMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, MenuMapper menuMapper) {
        this.userMapper = userMapper;
        this.menuMapper = menuMapper;
    }

    @Override
    public Boolean isValidToken(String token) {
        if (StringUtil.isEmpty(token)) {
            return false;
        }

        User user = userMapper.selectToken(token);
        if (user != null) {
            return user.getTokenExp().getTime() > System.currentTimeMillis();
        } else {
            return false;
        }
    }

    @Override
    public void updateToken(String token) {
        User user = userMapper.selectToken(token);
        userMapper.updateToken(user.getId(), token, new Timestamp(System.currentTimeMillis() + TOKEN_EXP));
    }

    @Override
    public User queryById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public JSONResult login(String email, String pwd) {
        User user = userMapper.selectByEmail(email);
        // 查询用户是否存在
        if (user == null) {
            return JSONResult.userNotExist();
        }
        // 检测密码
        else if (user.getPwd().equals(MD5Util.string2MD5(pwd))) {
            // 生成并存储Token令牌
            String token = UUID.randomUUID() + "";
            userMapper.updateToken(user.getId(), token, new Timestamp(System.currentTimeMillis() + TOKEN_EXP));

            return JSONResult.ok(token);
        }
        return JSONResult.errorMessage("密码错误");
    }

    @Override
    public JSONResult register(User user) {
        user.setPwd(MD5Util.string2MD5(user.getPwd()));
        // 参数错误
        if (StringUtil.isExistEmpty(user.getEmail(), user.getPwd())) {
            return JSONResult.build(ResultCode.PARAM_IS_INVALID, null);
        }
        // 用户名存在
        else if (userMapper.selectByEmail(user.getEmail()) != null) {
            return JSONResult.userNameExist();
        }
        // 注册用户
        else {
            int num = userMapper.insert(user);
            if (num == 1) {
                return JSONResult.ok(null);
            } else {
                return JSONResult.errorMessage("注册失败");
            }
        }
    }

    @Override
    public JSONResult getUserOfLogin(String token) {
        User user = userMapper.selectToken(token);
        if (user != null) {
            return JSONResult.ok(user);
        } else {
            return JSONResult.errorMessage("获取失败");
        }
    }

    @Override
    public JSONResult del(Long id) {
        if (userMapper.deleteByUid(id) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("删除失败");
    }

    @Override
    public JSONResult queryAll(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<User> users = userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return JSONResult.ok(pageInfo);
    }

    @Override
    public JSONResult getUserByToken(String token) {
        User user = userMapper.selectByToken(token);
        if (user != null) {
            // 系统管理员添加所有权限
            for (Role role : user.getRoles()) {
                if (role.getRid() == 1) {
                    List<Menu> menus = menuMapper.select();
                    role.setMenus(menus);
                    break;
                }
            }
            return JSONResult.ok(user);
        }
        return JSONResult.errorMessage("未知错误");
    }

    @Override
    public JSONResult set(User user) {
        user.setPwd(MD5Util.string2MD5(user.getPwd()));
        if (userMapper.update(user) > 0) {
            return JSONResult.ok("修改成功");
        }
        return JSONResult.errorMessage("修改失败");
    }

    @Override
    public JSONResult setRole(Long uid, List<Long> rids) {
        userMapper.deleteRoleByUid(uid);
        if (userMapper.insertRole(uid, rids) > 0) {
            return JSONResult.ok(userMapper.selectRoleByUid(uid));
        }
        return JSONResult.errorMessage("修改失败");
    }

    @Override
    public JSONResult setPortrait(User user) {
        if (userMapper.updatePortrait(user) > 0) {
            return JSONResult.ok("头像更新成功");
        }
        return JSONResult.errorMessage("头像更新失败");
    }

}
