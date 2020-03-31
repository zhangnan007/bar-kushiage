package com.bar.kushiage.dao;

import com.bar.kushiage.model.dto.Order;
import com.bar.kushiage.model.dto.OrderFoodLog;
import com.bar.kushiage.model.dto.OrderPayLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface OrderMapper {

    /**
     * 根据日期获取最大订单号
     * @param day
     * @return
     */
    @Select({"SELECT order_num as orderNum, create_time FROM `ORDER` WHERE create_time > #{day} ORDER BY create_time LIMIT 1"})
    public Map<String,Object> queryMaxOrderNumByDayTime(@Param("day") Long day);

    /**
     * 插入订单数据
     * @param order
     * @return
     */
    public int insert(Order order);

    /**
     * 插入订单菜品记录
     * @param orderFoodLog
     * @return
     */
    public int insertOrderFood(OrderFoodLog orderFoodLog);

    /**
     * 插入订单支付记录
     * @param orderPayLog
     * @return
     */
    public int insertOrderPay(OrderPayLog orderPayLog);
}
