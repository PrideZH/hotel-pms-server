package com.pengfu.pms.dao;

import com.pengfu.pms.entity.NotifyUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/24 - 14:34
 */
@Repository
public interface NotifyUserMapper {

    Long insert(NotifyUser notifyUser);

    List<NotifyUser> selectByRecipientId(Long id);

    Long update(NotifyUser notifyUser);
}
