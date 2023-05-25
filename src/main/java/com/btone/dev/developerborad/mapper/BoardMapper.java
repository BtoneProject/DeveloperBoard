package com.btone.dev.developerborad.mapper;

import com.btone.dev.developerborad.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    List<BoardVo> getBoardList();
    void create(@Param("boardInfo") Map<String, String> inputBoardInfo);
    BoardVo getBoardDetail(@Param("postNo") int postNo);
}
