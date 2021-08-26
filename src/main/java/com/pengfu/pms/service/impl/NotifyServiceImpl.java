package com.pengfu.pms.service.impl;

import com.pengfu.pms.dao.NotifyMapper;
import com.pengfu.pms.dao.NotifyUserMapper;
import com.pengfu.pms.entity.Notify;
import com.pengfu.pms.entity.NotifyUser;
import com.pengfu.pms.service.INotifyService;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/24 - 14:10
 */
@Service
public class NotifyServiceImpl implements INotifyService {

    private NotifyMapper notifyMapper;
    private NotifyUserMapper notifyUserMapper;

    @Autowired
    public NotifyServiceImpl(NotifyMapper notifyMapper, NotifyUserMapper notifyUserMapper) {
        this.notifyMapper = notifyMapper;
        this.notifyUserMapper = notifyUserMapper;
    }

    @Override
    public JSONResult add(Notify notify) {
        if (notifyMapper.insert(notify) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("发布失败");
    }

    @Override
    public JSONResult getAllByid(Long id) {
        // 拉取新的公告
        List<Notify> notifies = notifyMapper.selectNew(id);
        // 插入用户通知
        for (Notify notify : notifies) {
            NotifyUser notifyUser = new NotifyUser();
            notifyUser.setNotify(notify);
            notifyUser.setRecipientId(id);
            notifyUserMapper.insert(notifyUser);
        }
        return JSONResult.ok(notifyUserMapper.selectByRecipientId(id));
    }

    @Override
    public JSONResult set(Notify notify) {
        if (notifyMapper.update(notify) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("修改失败");
    }

    @Override
    public JSONResult setNotifyUser(NotifyUser notifyUser) {
        if (notifyUserMapper.update(notifyUser) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("修改失败");
    }

}
