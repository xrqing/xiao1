package com.xiao.xiao1.serviceImpl;/*
@auther XiaoRuiQing
@Date 2019/1/30
*/

import com.xiao.xiao1.entity.User;
import com.xiao.xiao1.mapper.UserMapper;
import com.xiao.xiao1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户通过用户名和密码
     */
    @Override
    public User getByPasswordAndUsername(String username, String password) {
        return userMapper.getByPasswordAndUsername(username, password);
    }

//    @Override
//    public User register(String username, String password) {
//        return userMapper.register(username, password);
//    }
}
