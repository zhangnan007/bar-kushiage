package com.bar.kushiage.model.vo.order;

import lombok.Data;

/**
 * 订单支付记录vo
 */
@Data
public class OrderPayLogVo {
    private String id;
    private String payVal; // 支付类型
    private String payTypeText; // 支付类型显示值
    private String payPrice; // 支付金额
}
