package com.bar.kushiage.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderPayLog {
    private Long id;

    private Long orderId;

    private String payType;

    private Double payPrice;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

}