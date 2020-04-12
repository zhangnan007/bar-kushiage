package com.bar.kushiage.service;

import com.bar.kushiage.model.vo.order.OrderVo;
import com.bar.kushiage.model.vo.order.QueryBillParamVo;
import com.bar.kushiage.model.vo.order.QueryBillTableVo;

import java.text.ParseException;
import java.util.Map;

/**
 * 订单服务
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderVo
     * @return
     */
    public Boolean create(OrderVo orderVo) throws ParseException;

    /**
     * 根据参数获取订单列表,分页
     *
     * @param queryBillParamVo
     * @return
     * @throws ParseException
     */
    public QueryBillTableVo queryByParam(QueryBillParamVo queryBillParamVo);

    /**
     * 获取指定订单详情
     *
     * @param orderId
     * @return
     * @throws ParseException
     */
    public OrderVo queryDetailsById(Long orderId);

    /**
     * 获取指定订单已选菜品信息
     *
     * @param orderId
     * @return
     */
    public Map<String, Object> queryOrderFoodByOrderId(Long orderId);

    /**
     * 获取指定订单支付明细
     *
     * @param orderId
     * @return
     */
    public Map<String, Object> queryOrderPayLogByOrderId(Long orderId);
}
