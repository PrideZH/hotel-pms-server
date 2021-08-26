package com.pengfu.pms.service;

import com.pengfu.pms.entity.Notify;
import com.pengfu.pms.entity.NotifyUser;
import com.pengfu.pms.util.JSONResult;

/**
 * @author PrideZH
 * @date 2021/8/24 - 14:10
 */
public interface INotifyService {

    /**
     * 添加公告
     * @param notify 公告信息
     * @return 响应信息
     */
    JSONResult add(Notify notify);

    /**
     * 读取最新的通知并添加到用户公告表中
     * 之后返回所有该用户未删除公告
     * @param id 用户ID
     * @return 该用户未删除的所有公告
     */
    JSONResult getAllByid(Long id);

    /**
     * 修改公告
     * @param notify 公告信息
     * @return 响应信息
     */
    JSONResult set(Notify notify);

    JSONResult setNotifyUser(NotifyUser notifyUser);
}
