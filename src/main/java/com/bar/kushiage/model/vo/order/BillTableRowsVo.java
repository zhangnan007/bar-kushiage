package com.bar.kushiage.model.vo.order;

import lombok.Data;

/**
 * 账单查询列表数据Vo
 */
@Data
public class BillTableRowsVo {
    private Long id; // 订单ID
    private String orderNum; // 订单号
    private Integer mealNum; // 取餐号
    private Double totalPrice; // 消费金额
    private String createTime; // 下单时间
}
