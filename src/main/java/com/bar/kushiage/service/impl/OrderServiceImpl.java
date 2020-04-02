package com.bar.kushiage.service.impl;

import com.bar.kushiage.common.Util;
import com.bar.kushiage.common.constant.ConstantEnum;
import com.bar.kushiage.dao.OrderMapper;
import com.bar.kushiage.model.dto.Order;
import com.bar.kushiage.model.dto.OrderFoodLog;
import com.bar.kushiage.model.dto.OrderPayLog;
import com.bar.kushiage.model.vo.order.OrderVo;
import com.bar.kushiage.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
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
    public Boolean create(OrderVo orderVo) throws ParseException {
        // 获取当天时间0点时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String begin = sdf.format(new Date()).split(" ")[0].trim() + " 00:00:00";
        Date day = sdf.parse(begin);
        // 获取订单号
        int orderNum = generateOrderNum(day);
        Order order = bulidOrder(orderVo, orderNum);
        orderMapper.insert(order);
        Date now = new Date();
        orderVo.getFoodList().forEach(item -> {
            OrderFoodLog foodLog = new OrderFoodLog();
            foodLog.setOrderId(order.getId());
            foodLog.setFoodName(item.getName());
            foodLog.setFoodPrice(item.getPrice());
            foodLog.setFoodSpecs(item.getSpecs());
            foodLog.setNum(item.getNum());
            foodLog.setStatus(ConstantEnum.DB_STATUS_NORMAL.getCode());
            foodLog.setCreateTime(now);
            foodLog.setModifyTime(now);
            orderMapper.insertOrderFood(foodLog);
        });
        orderVo.getPayInfo().forEach(item -> {
            OrderPayLog payLog = new OrderPayLog();
            payLog.setOrderId(order.getId());
            payLog.setPayType(item.getPayVal());
            payLog.setPayPrice(Util.parsePrice(item.getPayPrice()));
            payLog.setStatus(ConstantEnum.DB_STATUS_NORMAL.getCode());
            payLog.setCreateTime(now);
            payLog.setModifyTime(now);
            orderMapper.insertOrderPay(payLog);
        });
        return Boolean.TRUE;

    }

    /**
     * 构造order对象
     *
     * @param orderVo
     * @return
     */
    private Order bulidOrder(OrderVo orderVo, int orderNum) {
        Order order = new Order();
        order.setMealNum(orderVo.getMealNum());
        order.setOrderNum(orderNum);
        // 找零金额
        order.setChargePrice(Util.parsePrice(orderVo.getChangeMoney()));
        // 消费金额
        order.setConsumePrice(Util.parsePrice(orderVo.getTotalMoney()));
        // 实收金额
        order.setRealityPrice(Util.parsePrice(orderVo.getPaidMoney()));
        order.setStatus(ConstantEnum.DB_STATUS_NORMAL.getCode());
        Date now = new Date();
        order.setCreateTime(now);
        order.setModifyTime(now);
        return order;

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
                Integer orderNum = (Integer) map.get("orderNum");
                orderNum = orderNum + 1;
                orderNumHolder.set(orderNum);
                return orderNum;
            }
        }
    }
}