package com.btone.dev.developerborad.controller;

import com.btone.dev.developerborad.service.BoardService;
import com.btone.dev.developerborad.service.UserService;
import com.btone.dev.developerborad.vo.BoardVo;
import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/userList")
    public String getUserList(Model model) {
        List<UserVo> userList = userService.getUserList();

        for (UserVo userVo : userList) {
            System.out.println("userVo.getIdNo() = " + userVo.getIdNo());
            System.out.println("userVo.getId() = " + userVo.getId());
            System.out.println("userVo.getPassword() = " + userVo.getPassword());
            System.out.println("userVo.getEmail() = " + userVo.getEmail());
        }

        model.addAttribute("list", userList);
        return "userList";
    }



}
