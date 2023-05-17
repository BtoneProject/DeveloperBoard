package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserVo> getUserList();
}
