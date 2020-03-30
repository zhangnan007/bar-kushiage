package com.bar.kushiage.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * 订单数据库映射对象
 */
@Data
public class Order {
    private Long id; // 唯一标识
    private Integer order_num; // 订单号(日期+个数,例:202001200001)
    private Integer mealNum; // 取餐号
    private Double realityPrice; // 实收金额
    private Double consume_price; // 消费金额
    private Double charge_price; // 找零金额
    private Integer status; // 状态(1:生效,2:失效)
    private Date createTime; // 创建时间
    private Date modifyTime; // 修改时间
}
