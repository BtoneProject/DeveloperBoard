package com.btone.dev.developerborad.controller;

import com.btone.dev.developerborad.service.BoardService;
import com.btone.dev.developerborad.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public List<BoardVo> getBoardList() {
        List<BoardVo> boardList = boardService.getBoardList();

        return boardList;
    }
}
