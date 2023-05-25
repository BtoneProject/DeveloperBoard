package com.btone.dev.developerborad.controller;

import com.btone.dev.developerborad.service.UserService;
import com.btone.dev.developerborad.vo.KakaoProfile;
import com.btone.dev.developerborad.vo.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BoardController boardController;

    private final String KAKAO_GRANT_TYPE = "authorization_code";
    private final String KAKAO_CLIENT_ID = "48f96d8bb3a053ea613cb4ff71f25c10";
    private final String KAKAO_REDIRECT_URI = "http://localhost:8080/auth/kakao/callback";

    @GetMapping("/kakao/callback")
    public void kakaoCallback(@RequestParam String code, HttpServletResponse servletResponse) throws IOException { // data를 리턴해주는 컨트롤러 함수
        // POST방식으로 key=value 데이터를 요청(카카오쪽으로)
        RestTemplate tokenRequestRestTemplate = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders tokenRequestHeaders = new HttpHeaders();
        tokenRequestHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> tokenRequestParams = new LinkedMultiValueMap<>();
        tokenRequestParams.add("grant_type", KAKAO_GRANT_TYPE);
        tokenRequestParams.add("client_id", KAKAO_CLIENT_ID);
        tokenRequestParams.add("redirect_uri", KAKAO_REDIRECT_URI);
        tokenRequestParams.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =

                new HttpEntity<>(tokenRequestParams, tokenRequestHeaders);

        // Http요청하기 - Post방식으로 - 그리고 response 변수의 응답받음.
        ResponseEntity<String> tokenResponse = tokenRequestRestTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // ObjectMapper or Gson, Json Simple
        ObjectMapper tokenResponseObjectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;

        try {
            oAuthToken = tokenResponseObjectMapper.readValue(tokenResponse.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RestTemplate userInfoRestTemplate = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders userInfoHeaders = new HttpHeaders();
        userInfoHeaders.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        userInfoHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(userInfoHeaders);

        // Http요청하기 - Post방식으로 - 그리고 response 변수의 응답받음.
        ResponseEntity<String> userInfoResponse = userInfoRestTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper userInfoResponseObjectMapper = new ObjectMapper();
        userInfoResponseObjectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = userInfoResponseObjectMapper.readValue(userInfoResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // User coulmns in DB : idno, id, password, email, introduce
        UUID garbagePassword = UUID.randomUUID();

        String id = kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId();
        String email = kakaoProfile.getKakao_account().getEmail();
        String password = garbagePassword.toString();

        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("id", id);
        userMap.put("password", password);
        userMap.put("email", email);

        // 로그인순간 강제로 회원가입
        // 가입자 혹은 비가입자 체크해서 처리
        try {
            String msg = userService.duplicate(userMap);

            if (msg.contains("가능한")) {
                System.out.println("가입가능");
                userService.insertUser(userMap);
            }

            if (msg.contains("존재하는")) {
                System.out.println("가입불가");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        servletResponse.sendRedirect("http://localhost:3000");
    }

}
