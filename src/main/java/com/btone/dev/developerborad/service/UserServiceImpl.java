package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.mapper.UserMapper;
import com.btone.dev.developerborad.vo.UserVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public void insertUser(HashMap<String, String> map) {
        userMapper.insertUser(map);
    }


    @Override
    public UserVo login(Map<String, String> inputUserInfo) {
        System.out.println("inputUserInfo.get(\"id\") = " + inputUserInfo.get("id"));
        return userMapper.login(inputUserInfo);
    }
}
