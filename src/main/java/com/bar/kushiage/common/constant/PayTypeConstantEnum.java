package com.bar.kushiage.common.constant;

/**
 * 支付类型枚举
 */
public enum PayTypeConstantEnum {

    PAY_TYPE_ALIPAY("aliPay", "支付宝"),
    PAY_TYPE_WECHAT("weChat", "微信"),
    PAY_TYPE_CASE("cash", "现金"),
    PAY_TYPE_BANK_CARD("bankCard", "银行卡"),
    PAY_TYPE_REDUCTION("reduction", "减免"),
    PAY_TYPE_OTHERS("others", "其他");

    private final String val;
    private final String note;

    PayTypeConstantEnum(String val, String note) {
        this.val = val;
        this.note = note;
    }

    public String getVal() {
        return val;
    }

    public String getNote() {
        return note;
    }

    public static PayTypeConstantEnum getValueOf(String val) {
        for (PayTypeConstantEnum ce : values()) {
            if (ce.val.equals(val)) {
                return ce;
            }
        }
        return null;
    }
}
