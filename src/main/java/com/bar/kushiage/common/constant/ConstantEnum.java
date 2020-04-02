package com.bar.kushiage.common.constant;

/**
 * 菜品相关常量Enum
 */
public enum ConstantEnum {

    DB_STATUS_NORMAL(1,"生效"),
    DB_STATUS_LOSE(0,"失效");

    private final Integer code;
    private final String note;

    ConstantEnum(Integer code, String note) {
        this.code = code;
        this.note = note;
    }

    public Integer getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }

    public static ConstantEnum getValueOf(Integer code) {
        for (ConstantEnum ce : values()) {
            if (ce.code == code) {
                return ce;
            }
        }
        return null;
    }

}
