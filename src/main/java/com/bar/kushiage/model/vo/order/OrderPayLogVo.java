package com.bar.kushiage.model.vo.order;

import lombok.Data;

/**
 * 订单支付记录vo
 */
@Data
public class OrderPayLogVo {
    String id;
    String payVal; // 支付类型
    String payTypeText; // 支付类型显示值
    String payPrice; // 支付金额
}
