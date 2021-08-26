package com.pengfu.pms.service;

import com.pengfu.pms.entity.Customer;
import com.pengfu.pms.util.JSONResult;

/**
 * @author PrideZH
 * @date 2021/8/16 - 12:21
 */
public interface ICustomerService {

    JSONResult add(Customer customer);

    Customer addOver(Customer customer);

    JSONResult del(Long id);

    JSONResult getAll(Integer page, Integer limit);

    JSONResult findByIdCard(String idCard);

    JSONResult set(Customer customer);
}
