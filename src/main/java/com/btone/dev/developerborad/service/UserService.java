package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    List<UserVo> getUserList();
    void insertUser(HashMap<String, String> map);
    UserVo login(Map<String, String> inputUserInfo);

    // 회원정보 상세
    UserVo userInfo(Map<String, String> userInfo);
    // 회원정보 수정
    void updateUser(HashMap<String, String> map);
    // 회원탈퇴
    void deleteUser(HashMap<String, String> map);
}
