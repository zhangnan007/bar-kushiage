package com.bar.kushiage.model.vo.order;

import lombok.Data;

import java.util.List;

/**
 * 订单vo
 */
@Data
public class OrderVo {
    List<OrderFoodVo> foodList; // 订单菜品
    List<OrderPayLogVo> payInfo; // 支付记录
    String totalMoney; // 总消费金额
    String paidMoney; // 已付金额
    String changeMoney; // 找零金额
}
