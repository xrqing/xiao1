package com.xiao.xiao1.service;
/*
@auther XiaoRuiQing
@Date 2019/1/30
*/

import com.xiao.xiao1.entity.User;

public interface UserService {
    /**
     * 查询用户通过用户名和密码
     */
    User getByPasswordAndUsername(String username, String password);

//    /** 注册*/
//    User register(String username,String password);
}

