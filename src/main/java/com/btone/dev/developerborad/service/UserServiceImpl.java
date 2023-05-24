package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.mapper.UserMapper;
import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

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
        UserVo user = userMapper.login(inputUserInfo);

        validateUser(user);

        // 아이디 틀리거나 없거나
        // 비밀번호 틀리거나

        return user;
    }

    public void validateUser(UserVo user) {
        if (user.getId() == null) {
            System.out.println("아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
        }
    }

    // 회원정보 상세
    @Override
    public UserVo userInfo(Map<String, String> userInfo) {
//        System.out.println("----------- 데이터 들어오나 확인 " + userInfo);
        return userMapper.userInfo(userInfo);
    }

    // 회원정보 수정
    @Override
    public void updateUser(HashMap<String, String> map) {
        userMapper.updateUser(map);
    }

    // 회원정보 삭제
    @Override
    public void deleteUser(HashMap<String, String> map) {
        userMapper.deleteUser(map);
    }

}
