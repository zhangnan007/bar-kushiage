package com.bar.kushiage.model.vo;

import lombok.Data;

/**
 * 菜品信息VO
 */
@Data
public class FoodVo {
    private Long id; // 菜品唯一标识
    private String name; // 菜品名称
    private Long sort; // 前台显示位置依据，按销量排序，销量越大该字段值越大
    private String specs; // 菜品规格
    private Double price; // 价格
    private String code; // 查询用速记码,餐品名称首字母
    private Long typeId; // 菜品所属类型
}