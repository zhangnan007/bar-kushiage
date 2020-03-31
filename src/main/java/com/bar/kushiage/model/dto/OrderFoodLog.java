package com.bar.kushiage.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderFoodLog {
    private Long id;

    private Long orderId;

    private String foodName;

    private String foodSpecs;

    private Double foodPrice;

    private Integer num;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

}