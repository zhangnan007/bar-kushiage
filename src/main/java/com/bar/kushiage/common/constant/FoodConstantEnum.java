package com.bar.kushiage.common.constant;

/**
 * 菜品相关常量Enum
 */
public enum FoodConstantEnum {

    FOOD_STATUS_NORMAL(1,"生效"),
    FOOD_STATUS_LOSE(0,"失效");

    private final Integer code;
    private final String note;

    FoodConstantEnum(Integer code, String note) {
        this.code = code;
        this.note = note;
    }

    public Integer getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }

    public static FoodConstantEnum getValueOf(Integer code) {
        for (FoodConstantEnum ce : values()) {
            if (ce.code == code) {
                return ce;
            }
        }
        return null;
    }

}
