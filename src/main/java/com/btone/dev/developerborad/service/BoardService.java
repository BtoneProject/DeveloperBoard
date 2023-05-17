package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.mapper.BoardMapper;
import com.btone.dev.developerborad.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper BoardMapper;

    public List<BoardVo> getBoardList() {
        return BoardMapper.getBoardList();
    }

}
