package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.vo.BoardVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BoardService {

    List<BoardVo> getBoardList();
    void create(Map<String, String> inputBoardInfo);
    BoardVo getBoardDetail(Map<String, Integer> boardInfo);
}
