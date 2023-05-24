package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.common.AES;
import com.btone.dev.developerborad.mapper.UserMapper;
import com.btone.dev.developerborad.vo.UserVo;
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
    public void insertUser(HashMap<String, String> map) throws Exception {
        //비밀번호 암호화
        AES aes = new AES();
        String password = aes.encrypt(map.get("password"));
        map.put("password", password);
        userMapper.insertUser(map);
    }


    @Override
    public UserVo login(Map<String, String> inputUserInfo) {
        System.out.println("inputUserInfo.get(\"id\") = " + inputUserInfo.get("id"));
        return userMapper.login(inputUserInfo);
    }

    @Override
    public UserVo duplicate(HashMap<String, String> map) {
        return userMapper.duplicate(map);
    }
}
