package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@SuppressWarnings("all")
@RestController
public class UserController {
    Gson gson = new Gson();
    @Autowired
    private UserMapper userMapper;

    // 登录
    @PostMapping("/login")
    public int login(@RequestBody User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(user);
        if (userMapper.selectOne(userQueryWrapper) == null) {
            return -1;
        }
        return 1;
    }

    // 注册
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userMapper.insert(user);
    }
}
