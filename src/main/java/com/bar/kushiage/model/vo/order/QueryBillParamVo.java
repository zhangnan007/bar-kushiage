package com.bar.kushiage.model.vo.order;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 根据条件查询账单数据Vo
 */
@Data
public class QueryBillParamVo {
    private Double conPrice; // 消费金额
    private Integer mealNum; // 取餐号
    private String orderNum; // 订单号
    private String stimeStr; // 映射前端开始时间
    private String etimeStr; // 映射前端结束时间
    private Timestamp startTime; // 后台逻辑用开始时间
    private Timestamp endTime; // 后台逻辑用结束时间
    private Integer pageNum = 1; // 当前页码
    private Integer pageSize = 2; // 每页显示条数
}
