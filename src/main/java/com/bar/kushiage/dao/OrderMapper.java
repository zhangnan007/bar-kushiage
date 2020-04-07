package com.bar.kushiage.dao;

import com.bar.kushiage.model.dto.Order;
import com.bar.kushiage.model.dto.OrderFoodLog;
import com.bar.kushiage.model.dto.OrderPayLog;
import com.bar.kushiage.model.vo.order.OrderVo;
import com.bar.kushiage.model.vo.order.QueryBillParamVo;
import org.apache.ibatis.annotations.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderMapper {

    /**
     * 根据日期获取最大订单号
     *
     * @param day
     * @return
     */
    @Select("SELECT order_num as orderNum, create_time FROM `order` WHERE create_time > #{day} ORDER BY create_time LIMIT 1")
    public Map<String, Object> queryMaxOrderNumByDayTime(@Param("day") Date day);

    /**
     * 获取订单信息
     * @param param
     * @return
     */
    public List<Order> selectByParam(@Param("param") QueryBillParamVo param);

    /**
     * 插入订单数据
     *
     * @param order
     * @return
     */
    @Insert("INSERT INTO `order`" +
            " (order_num, meal_num, reality_price, consume_price, charge_price, `status`, create_time, modify_time) " +
            "VALUES " +
            "(#{order.orderNum},#{order.mealNum},#{order.realityPrice},#{order.consumePrice},#{order.chargePrice},#{order.status},#{order.createTime},#{order.modifyTime})")
    @Options(keyProperty="id", useGeneratedKeys=true, keyColumn="id")
    public int insert(@Param("order") Order order);

    /**
     * 插入订单菜品记录
     *
     * @param orderFoodLog
     * @return
     */
    @Insert("INSERT INTO order_food_log (order_id, food_name, food_specs, food_price, num, `status`, create_time, modify_time) " +
            "VALUES " +
            "(#{orderFoodLog.orderId},#{orderFoodLog.foodName},#{orderFoodLog.foodSpecs},#{orderFoodLog.foodPrice},#{orderFoodLog.num},#{orderFoodLog.status},#{orderFoodLog.createTime},#{orderFoodLog.modifyTime})")
    public int insertOrderFood(@Param("orderFoodLog") OrderFoodLog orderFoodLog);

    /**
     * 插入订单支付记录
     *
     * @param orderPayLog
     * @return
     */
    @Insert("INSERT INTO order_pay_log (order_id, pay_type, pay_price, `status`, create_time, modify_time) " +
            "VALUES " +
            "(#{orderPayLog.orderId},#{orderPayLog.payType},#{orderPayLog.payPrice},#{orderPayLog.status},#{orderPayLog.createTime},#{orderPayLog.modifyTime})")
    public int insertOrderPay(@Param("orderPayLog") OrderPayLog orderPayLog);
}
