package com.bar.kushiage.service.impl;

import com.bar.kushiage.dao.OrderExtMapper;
import com.bar.kushiage.model.vo.order.OrderVo;
import com.bar.kushiage.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    // 订单号自增序列
    private static AtomicInteger orderNumHolder = new AtomicInteger(0);

    @Autowired
    OrderExtMapper orderExtMapper;

    @Transactional
    @Override
    public Boolean create(OrderVo orderVo) {
        try{
            // 获取当天时间
//            createOrderNum();
            return Boolean.TRUE;
        }catch (Exception e){
            logger.error("OrderService create error ",e);
            throw e;
        }
    }

    /**
     * 获取当天订单序列的下一个值
     * @param currDay
     * @return
     */
    private synchronized int createOrderNum(Long currDay){
        int num = orderNumHolder.incrementAndGet();
        if(num > 1){
           return num;
        } else {
            // 进行初始化
            // 查询数据库当天最大订单号
            Map<String,Object> map = orderExtMapper.queryMaxOrderNumByDayTime(currDay);
            // 判断如果没有则直接加1并返回
            if(map == null || map.size() <= 0){
                return num;
            } else {
                // 否则获取最大数加一再返回
                String orderNum = (String)map.get("orderNum");
                Integer nextNum = Integer.parseInt(orderNum.substring(orderNum.length() - 4)) + 1;
                orderNumHolder.set(nextNum);
                return nextNum;
            }
        }
    }
}
