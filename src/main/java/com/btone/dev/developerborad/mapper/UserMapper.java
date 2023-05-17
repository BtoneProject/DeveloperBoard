package com.btone.dev.developerborad.mapper;

import com.btone.dev.developerborad.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVo> getUserList();
}
