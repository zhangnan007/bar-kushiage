package com.bar.kushiage.service;

import com.bar.kushiage.model.dto.Order;
import com.bar.kushiage.model.vo.order.OrderVo;
import com.bar.kushiage.model.vo.order.QueryBillParamVo;
import com.bar.kushiage.model.vo.order.QueryBillTableVo;

import java.text.ParseException;
import java.util.List;

/**
 * 订单服务
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderVo
     * @return
     */
    public Boolean create(OrderVo orderVo)throws ParseException;

    /**
     * 根据参数获取订单列表,分页
     * @param queryBillParamVo
     * @return
     * @throws ParseException
     */
    public QueryBillTableVo queryByParam(QueryBillParamVo queryBillParamVo);

    /**
     * 获取指定订单详情
     * @param orderId
     * @return
     * @throws ParseException
     */
    public OrderVo queryDetailsById(Long orderId);
}
