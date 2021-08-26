package com.pengfu.pms.dao;

import com.pengfu.pms.entity.Notify;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/24 - 14:15
 */
@Repository
public interface NotifyMapper {

    Long insert(Notify notify);

    /**
     * 根据未拉取的公告
     * @param id 用户ID
     * @return 公告
     */
    List<Notify> selectNew(Long id);

    Long update(Notify notify);
}
