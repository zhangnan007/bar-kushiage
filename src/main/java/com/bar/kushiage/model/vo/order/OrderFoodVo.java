package com.bar.kushiage.model.vo.order;

import lombok.Data;

/**
 * 提交订单时的菜品信息VO
 */
@Data
public class OrderFoodVo {
    private String id; // 菜品唯一标识
    private String name; // 菜品名称
    private String specs; // 菜品规格
    private Double price; // 点餐时菜品单价
    private Double totalPrice; // 总价格
    private Integer num; // 数量

}