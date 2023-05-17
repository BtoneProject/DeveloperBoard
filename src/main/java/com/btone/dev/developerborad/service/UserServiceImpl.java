package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.mapper.UserMapper;
import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    public List<UserVo> getUserList() {
        return userMapper.getUserList();
    }
}