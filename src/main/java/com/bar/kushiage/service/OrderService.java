package com.bar.kushiage.service;

import com.bar.kushiage.model.vo.order.OrderVo;

/**
 * 订单服务
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderVo
     * @return
     */
    public Boolean create(OrderVo orderVo);
}
