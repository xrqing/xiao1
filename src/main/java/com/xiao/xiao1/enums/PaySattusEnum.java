package com.xiao.xiao1.enums;

/*
@auther XiaoRuiQing
@Date 2019/1/23
*/

import lombok.Getter;


@Getter
public enum PaySattusEnum implements CodeEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;
    private String message;

    PaySattusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
