package com.bar.kushiage.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Food {
    private Long id;

    private Long typeId;

    private String name;

    private String helpCode;

    private String createMan;

    private String remarks;

    private String loseReason;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

}