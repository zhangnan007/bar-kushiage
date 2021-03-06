package com.bar.kushiage.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FoodType {
    private Long id;

    private String name;

    private String remark;

    private String loseReason;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

}