package com.bar.kushiage.model.vo.order;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单vo
 */
@Data
public class OrderVo {
    private List<OrderFoodVo> foodList; // 订单菜品
    private List<OrderPayLogVo> payInfo; // 支付记录
    private Long id; // 订单ID
    private Long orderNum; // 订单号
    private Integer mealNum; // 取餐号
    private String totalMoney; // 总消费金额
    private String paidMoney; // 已付金额
    private String changeMoney; // 找零金额

    public String bulidFoodIds(){
        return this.foodList.stream().map(OrderFoodVo :: getId).collect(Collectors.joining(";"));
    }
}
