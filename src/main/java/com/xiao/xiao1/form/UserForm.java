package com.xiao.xiao1.form;/*
@auther XiaoRuiQing
@Date 2019/1/30
*/

import lombok.Data;

@Data
public class UserForm {
    /**
     * 用户的id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
