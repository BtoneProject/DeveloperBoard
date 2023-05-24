package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.mapper.BoardMapper;
import com.btone.dev.developerborad.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;
    public List<BoardVo> getBoardList() {
        return boardMapper.getBoardList();
    }

    @Override
    public void create(Map<String, String> inputBoardInfo) {
        boardMapper.create(inputBoardInfo);
    }
}
