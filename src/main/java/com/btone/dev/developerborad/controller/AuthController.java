package com.btone.dev.developerborad.controller;

import com.btone.dev.developerborad.service.UserService;
import com.btone.dev.developerborad.vo.KakaoProfile;
import com.btone.dev.developerborad.vo.OAuthToken;
import com.btone.dev.developerborad.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    private final String KAKAO_GRANT_TYPE = "authorization_code";
    private final String KAKAO_CLIENT_ID = "48f96d8bb3a053ea613cb4ff71f25c10";
    private final String KAKAO_REDIRECT_URI = "http://localhost:8080/auth/kakao/callback";

    @GetMapping("/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) { // data를 리턴해주는 컨트롤러 함수
        System.out.println("===== authController =====");
        System.out.println("code value = " + code);

        // POST방식으로 key=value 데이터를 요청(카카오쪽으로)
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", KAKAO_GRANT_TYPE);
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("redirect_uri", KAKAO_REDIRECT_URI);
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Http요청하기 - Post방식으로 - 그리고 response 변수의 응답받음.
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;

        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("카카오 액세스 토큰 = " + oAuthToken.getAccess_token());

        RestTemplate restTemplate2 = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        // Http요청하기 - Post방식으로 - 그리고 response 변수의 응답받음.
        ResponseEntity<String> response2 = restTemplate2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // User 오브젝트 : idno, id, password, email, introduce
        UUID garbagePassword = UUID.randomUUID();

        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        System.out.println("==================================");
        System.out.println("게시판 아이디: " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("게시판 이메일: " + kakaoProfile.getKakao_account().getEmail());
        System.out.println("게시판 패스워드: " + garbagePassword);

        UserVo userVo = new UserVo();
        userVo.setId(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        userVo.setEmail(kakaoProfile.getKakao_account().getEmail());
        userVo.setPassword(garbagePassword.toString());

        // 로그인순간 강제로 회원가입
//        userService.insertUser();

        return response2.getBody();
    }

}
