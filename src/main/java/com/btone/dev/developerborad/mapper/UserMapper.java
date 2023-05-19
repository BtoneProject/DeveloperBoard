package com.btone.dev.developerborad.mapper;

import com.btone.dev.developerborad.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<UserVo> getUserList();
    UserVo login(@Param("userInfo") Map<String, String> inputUserInfo);
}
