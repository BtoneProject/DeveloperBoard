package com.btone.dev.developerborad.controller;

import com.btone.dev.developerborad.enums.Status;
import com.btone.dev.developerborad.service.UserService;
import com.btone.dev.developerborad.vo.Message;
import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
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
    public ResponseEntity<Message> login(@RequestBody Map<String, String> inputUserInfo) {
        UserVo user = userService.login(inputUserInfo);

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(Status.OK);
        message.setMessage("LOGIN SUCCESS");
        message.setData(user);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback() { // data를 리턴해주는 컨트롤러 함수
        System.out.println("카카오 콜백");

        return "카카오 인증완료";
    }

    //회원가입
    @PostMapping("/join")
    public void insertUser(@RequestBody HashMap<String, String> map)  {
        userService.insertUser(map);
    }

}
