package com.btone.dev.developerborad.controller;

import com.btone.dev.developerborad.service.UserService;
import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //회원가입
    @PostMapping("/join")
    public void insertUser(@RequestBody HashMap<String, String> map) throws Exception {
        userService.insertUser(map);
    }


}
