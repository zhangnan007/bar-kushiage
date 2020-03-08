package com.bar.kushiage.model.dto;

import java.util.Date;

public class Food {
    private Long id;

    private Integer foodTypeId;

    private String foodName;

    private Double foodPrice;

    private String foodMnemonicCode;

    private Date createTime;

    private Date modifyTime;

    private Integer status;

    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodMnemonicCode() {
        return foodMnemonicCode;
    }

    public void setFoodMnemonicCode(String foodMnemonicCode) {
        this.foodMnemonicCode = foodMnemonicCode == null ? null : foodMnemonicCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}