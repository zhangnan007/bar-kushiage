package com.bar.kushiage.model.vo.order;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单vo
 */
@Data
public class OrderVo {
    List<OrderFoodVo> foodList; // 订单菜品
    List<OrderPayLogVo> payInfo; // 支付记录
    Integer mealNum; // 取餐号
    String totalMoney; // 总消费金额
    String paidMoney; // 已付金额
    String changeMoney; // 找零金额

    public String bulidFoodIds(){
        return this.foodList.stream().map(OrderFoodVo :: getId).collect(Collectors.joining(";"));
    }
}
