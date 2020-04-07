package com.bar.kushiage.model.vo.order;

import lombok.Data;

import java.util.Date;

/**
 * 根据条件查询账单数据Vo
 */
@Data
public class QueryBillParamVo {
    private Double conPrice; // 消费金额
    private Integer mealNum; // 取餐号
    private Integer orderNum; // 订单号
    private Date startTime; // 开始时间
    private Date endTime; // 结束时间
    private Integer pageNum = 1; // 当前页码
    private Integer pageSize = 2; // 每页显示条数
}
