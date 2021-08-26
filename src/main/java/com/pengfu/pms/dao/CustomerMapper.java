package com.pengfu.pms.dao;

import com.pengfu.pms.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/16 - 12:17
 */
@Repository
public interface CustomerMapper {

    Long insert(Customer customer);

    List<Customer> select();

    Customer selectById(Long cid);

    Customer selectByInfo(Customer customer);

    Customer selectByIdCard(String idCard);

    Long update(Customer customer);

    Long deleteById(Long id);

    Integer today();

    Integer total();
}
