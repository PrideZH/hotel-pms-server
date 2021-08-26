package com.pengfu.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengfu.pms.dao.CustomerMapper;
import com.pengfu.pms.entity.Customer;
import com.pengfu.pms.service.ICustomerService;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/16 - 12:21
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public JSONResult add(Customer customer) {

        return null;
    }

    @Override
    public Customer addOver(Customer customer) {
        Customer customerTemp = customerMapper.selectByInfo(customer);
        if (customerTemp != null) {
            return customerTemp;
        }
        if (customerMapper.insert(customer) > 0) {
            return customer;
        }
        return null;
    }

    @Override
    public JSONResult del(Long id) {
        if (customerMapper.deleteById(id) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("删除失败");
    }

    @Override
    public JSONResult getAll(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Customer> customers = customerMapper.select();
        PageInfo<Customer> pageInfo = new PageInfo<>(customers);
        return JSONResult.ok(pageInfo);
    }

    @Override
    public JSONResult findByIdCard(String idCard) {
        Customer customer = customerMapper.selectByIdCard(idCard);
        if (customer == null) {
            return JSONResult.ok(null);
        }
        return JSONResult.ok(customer);
    }

    @Override
    public JSONResult set(Customer customer) {
        if (customerMapper.update(customer) > 0) {
            return JSONResult.ok(null);
        }
        return JSONResult.errorMessage("修改失败");
    }

}
