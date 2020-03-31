package com.bar.kushiage.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;

    private Integer orderNum;

    private Integer mealNum;

    private Double realityPrice;

    private Double consumePrice;

    private Double chargePrice;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

}