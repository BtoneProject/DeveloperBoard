package com.btone.dev.developerborad.controller;

import com.btone.dev.developerborad.service.UserService;
import com.btone.dev.developerborad.vo.UserVo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public String getUserList(Model model) {
        List<UserVo> userList = userService.getUserList();
        model.addAttribute("list", userList);

        return "userList";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody Map<String, String> inputUserInfo) {
        UserVo user = userService.login(inputUserInfo);

        for (String value : inputUserInfo.values()) {
            System.out.println("value = " + value);
        }
    }

    @PostMapping("/join")
    public void insertUser(@RequestBody HashMap<String, String> map) {
        userService.insertUser(map);
    }

    //MyInfo
    @RequestMapping(value = "/myInfo", method = RequestMethod.POST)
    public UserVo myInfo(@RequestBody Map<String, String> userInfo) {
        return userService.userInfo(userInfo);
    }

    // 회원정보 수정
    @PostMapping("/updateUser")
    public void updateUser(@RequestBody HashMap<String, String> map) {
        userService.updateUser(map);
    }

    // 회원정보 삭제
    @PostMapping("/deleteUser")
    public void deleteUser(@RequestBody HashMap<String, String> map) {
        userService.deleteUser(map);
    }


}
