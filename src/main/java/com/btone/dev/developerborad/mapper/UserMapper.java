package com.btone.dev.developerborad.mapper;

import com.btone.dev.developerborad.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<UserVo> getUserList();

    void insertUser(@Param("joinInfo") HashMap<String, String> map);
    UserVo login(@Param("userInfo") Map<String, String> inputUserInfo);

    // User
    UserVo userInfo(@Param("loggedInUser") Map<String, String> userInfo);
    void updateUser(@Param("userInfo") HashMap<String, String> map);
    void deleteUser(@Param("userInfo") HashMap<String, String> map);
}
