package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    List<UserVo> getUserList();
    UserVo login(Map<String, String> inputUserInfo);
}
