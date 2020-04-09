package com.bar.kushiage.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Order {
    private Long id;

    private String orderNum;

    private Integer mealNum;

    private Double realityPrice;

    private Double consumePrice;

    private Double chargePrice;

    private Integer status;

    private Timestamp createTime;

    private Timestamp modifyTime;

}