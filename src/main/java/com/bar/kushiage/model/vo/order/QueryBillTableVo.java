package com.bar.kushiage.model.vo.order;

import lombok.Data;

import java.util.List;

/**
 * 查询账单列表Vo
 */
@Data
public class QueryBillTableVo {
    private Long total = 0L; // 总条数
    private List<BillTableRowsVo> rows;
}
