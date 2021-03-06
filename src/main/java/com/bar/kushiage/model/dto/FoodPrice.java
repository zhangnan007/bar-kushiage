package com.bar.kushiage.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FoodPrice {
    private Long id;

    private Long foodId;

    private String specs;

    private Double price;

    private Long sort;

    private String loseReason;

    private String remark;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

}