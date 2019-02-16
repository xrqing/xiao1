package com.xiao.xiao1.vo;
/*
@auther XiaoRuiQing
@Date 2019/1/23
*/

import lombok.Data;


/*
 * 买家商品
 */
@Data
public class ResultVo<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
