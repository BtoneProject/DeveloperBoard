package com.btone.dev.developerborad.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVo {
    private int postNo;
    private String title;
    private String content;
    private String writer;
    private Date date;
    private int viewCount;

    private int idNo;
}
