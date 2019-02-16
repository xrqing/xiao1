package com.xiao.xiao1.controller;
/*
@auther XiaoRuiQing
@Date 2019/1/30
*/

import com.xiao.xiao1.entity.User;
import com.xiao.xiao1.enums.ResultEnum;
import com.xiao.xiao1.form.UserForm;
import com.xiao.xiao1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/seller/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("common/login");
    }

    /**
     * 登录的方法
     */
    @PostMapping("/dologin")
    public ModelAndView doLogin(@Valid UserForm form,
                                BindingResult bindingResult,
                                Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/user/login");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.user_login_success.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
