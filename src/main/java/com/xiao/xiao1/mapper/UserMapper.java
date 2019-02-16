package com.xiao.xiao1.mapper;

/*
@auther XiaoRuiQing
@Date 2019/1/30
*/

import com.xiao.xiao1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User, Integer> {

    /**
     * 查询用户通过用户名和密码
     */
    User getByPasswordAndUsername(String username, String password);

//    /** 注册*/
//    User register(String username,String password);
}
