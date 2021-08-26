package com.pengfu.pms.service.impl;

import com.pengfu.pms.dao.CustomerMapper;
import com.pengfu.pms.dao.OrderMapper;
import com.pengfu.pms.entity.Order;
import com.pengfu.pms.model.Statistical;
import com.pengfu.pms.model.Turnover;
import com.pengfu.pms.service.IStatisticalService;
import com.pengfu.pms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author PrideZH
 * @date 2021/8/25 - 12:50
 */
@Service
public class StatisticalServiceImpl implements IStatisticalService {

    private OrderMapper orderMapper;
    private CustomerMapper customerMapper;

    @Autowired
    public StatisticalServiceImpl(OrderMapper orderMapper, CustomerMapper customerMapper) {
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public JSONResult get() {
        Statistical statistical = new Statistical();
        statistical.setTodayOrder(orderMapper.today());
        statistical.setTotalOrder(orderMapper.total());
        statistical.setTodayCustomer(customerMapper.today());
        statistical.setTotalCustomer(customerMapper.total());
        return JSONResult.ok(statistical);
    }


    @Override
    public JSONResult getTurnover() {
        Double yearAmount = .0, monthAmount = .0, dayAmount = .0;
        Double beforeMonthAmount = .0, beforeDayAmount = .0;

        Double[] revenues = new Double[7];
        for (int i = 0; i < 7; i++) {
            revenues[i] = new Double(0);
        }

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);

        SimpleDateFormat monthSdf = new SimpleDateFormat("M");
        SimpleDateFormat daySdf = new SimpleDateFormat("d");

        long beforeWeekDay = System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000;

        List<Order> orders = orderMapper.selectYear();
        for (Order order : orders) {
            Date date = new Date(order.getPaymentTime().getTime());
            yearAmount += order.getTotalAmount();
            if (Integer.parseInt(monthSdf.format(date)) == month) {
                monthAmount += order.getTotalAmount();
                if (Integer.parseInt(daySdf.format(order.getPaymentTime())) == day) {
                    dayAmount += order.getTotalAmount();
                } else if (Integer.parseInt(daySdf.format(order.getPaymentTime())) == day - 1) {
                    beforeDayAmount += order.getTotalAmount();
                }
            } else if (Integer.parseInt(monthSdf.format(date)) == month - 1) {
                beforeMonthAmount += order.getTotalAmount();
            }

            if (order.getPaymentTime().getTime() > beforeWeekDay) {
                revenues[(int)(order.getPaymentTime().getTime() - beforeWeekDay) / (24 * 60 * 60 * 1000)]
                        += order.getTotalAmount();
            }
        }

        Turnover turnover = new Turnover();
        turnover.setYearAmount(yearAmount);
        turnover.setMonthAmount(monthAmount);
        turnover.setDayAmount(dayAmount);
        if (beforeMonthAmount != 0) {
            turnover.setMonthly((monthAmount - beforeMonthAmount) / beforeMonthAmount * 100);
        } else {
            turnover.setMonthly(100.0);
        }
        if (beforeDayAmount != 0) {
            turnover.setDaily((dayAmount - beforeDayAmount) / beforeDayAmount * 100);
        } else {
            turnover.setDaily(100.0);
        }

        turnover.setRevenues(revenues);

        return JSONResult.ok(turnover);
    }

}
