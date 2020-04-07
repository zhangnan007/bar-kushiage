package com.bar.kushiage.model.vo.order;

import lombok.Data;

import java.util.Date;

/**
 * 账单查询列表数据Vo
 */
@Data
public class BillTableRowsVo {
    Long id; // 订单ID
    Integer orderNum; // 订单号
    Integer mealNum; // 取餐号
    Double totalPrice; // 消费金额
    String createTime; // 下单时间
}
