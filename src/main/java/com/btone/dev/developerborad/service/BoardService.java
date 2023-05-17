package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.vo.BoardVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {

    List<BoardVo> getBoardList();

}
