package com.xiao.xiao1.utils;/*
@auther XiaoRuiQing
@Date 2019/1/28
*/

import com.xiao.xiao1.enums.CodeEnum;

/**
 * 枚举的工具类
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
