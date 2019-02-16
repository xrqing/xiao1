package com.xiao.xiao1.exception;

/*
@auther XiaoRuiQing
@Date 2019/1/24
*/


import com.xiao.xiao1.enums.ResultEnum;

/**
 * 异常
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
