package com.xiao.xiao1.utils;
/*
@auther XiaoRuiQing
@Date 2019/1/24
*/

import java.util.Random;

/**
 * 生成随机数的方法
 */
public class KeyUtil {

    /**
     * 生成唯一的主建（格式：时间+随机数）
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
