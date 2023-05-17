package com.btone.dev.developerborad.mapper;

import com.btone.dev.developerborad.vo.BoardVo;
import com.btone.dev.developerborad.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVo> getBoardList();
}
