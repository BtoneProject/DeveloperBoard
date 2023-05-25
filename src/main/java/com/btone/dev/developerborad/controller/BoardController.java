package com.btone.dev.developerborad.controller;

import com.btone.dev.developerborad.service.BoardService;
import com.btone.dev.developerborad.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void login(@RequestBody Map<String, String> inputBoardInfo) {
        boardService.create(inputBoardInfo);
    }

    @RequestMapping(value = "/boardDetail", method = RequestMethod.POST)
    public BoardVo getBoardDetail(@RequestBody Map<String, Integer> boardInfo) { return boardService.getBoardDetail(boardInfo); }
}
