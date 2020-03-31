package com.bar.kushiage.service.impl;

import com.bar.kushiage.dao.OrderMapper;
import com.bar.kushiage.model.vo.order.OrderVo;
import com.bar.kushiage.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    // 订单号自增序列
    private static AtomicInteger orderNumHolder = new AtomicInteger(0);

    @Autowired
    OrderMapper orderMapper;

    @Transactional
    @Override
    public Boolean create(OrderVo orderVo) {
        try {
            // 获取当天时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String begin = sdf.format(new Date()).split(" ")[0].trim() + " 00:00:00";
            Date day = sdf.parse(begin);
            int orderNum = generateOrderNum(day);

            return Boolean.TRUE;
        } catch (Exception e) {
            logger.error("OrderService create error ", e);
            throw e;
        }
    }

    /**
     * 生成订单序列号
     *
     * @param currDay
     * @return
     */
    private synchronized int generateOrderNum(Date currDay) {
        int num = orderNumHolder.incrementAndGet();
        if (num > 1) {
            return num;
        } else {
            // 进行初始化
            // 查询数据库当天最大订单号
            Map<String, Object> map = orderMapper.queryMaxOrderNumByDayTime(currDay);
            // 判断如果没有则直接加1并返回
            if (map == null || map.size() <= 0) {
                return num;
            } else {
                // 否则获取最大数加一再返回
                String orderNum = (String) map.get("orderNum");
                Integer nextNum = Integer.parseInt(orderNum.substring(orderNum.length() - 4)) + 1;
                orderNumHolder.set(nextNum);
                return nextNum;
            }
        }
    }
}
