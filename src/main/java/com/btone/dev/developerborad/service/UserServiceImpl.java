package com.btone.dev.developerborad.service;

import com.btone.dev.developerborad.common.AES;
import com.btone.dev.developerborad.mapper.UserMapper;
import com.btone.dev.developerborad.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserVo> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public void insertUser(HashMap<String, String> map) throws Exception {

        //비밀번호 암호화
//        AES aes = new AES();
//        String password = aes.encrypt(map.get("password"));

        //spring security로 암호화
        String password = passwordEncoder.encode(map.get("password"));
        map.put("password", password);
        userMapper.insertUser(map);
    }


    @Override
    public UserVo login(Map<String, String> inputUserInfo) {
        System.out.println("inputUserInfo.get(\"id\") = " + inputUserInfo.get("id"));
        UserVo vo = userMapper.login(inputUserInfo);
        if(passwordEncoder.matches(inputUserInfo.get("password"), vo.getPassword())){
            System.out.println("비밀번호 일치");
        }else{
            System.out.println("비밀번호 다름");
        }
        return userMapper.login(inputUserInfo);
    }

    //회원가입 시 아이디 중복 체크
    @Override
    public String duplicate(HashMap<String, String> map) {
        String msg = "사용 가능한 아이디입니다.";
        if(userMapper.duplicate(map) != null){
            msg = "이미 존재하는 아이디입니다.";
        }
        return msg;
    }
    
    public void validateUser(UserVo user) {
        if (user.getId() == null) {
            System.out.println("아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
        }
    }

    // 회원정보 상세
    @Override
    public UserVo userInfo(Map<String, String> userInfo) {
//        System.out.println("----------- 데이터 들어오나 확인 " + userInfo);
        return userMapper.userInfo(userInfo);
    }

    // 회원정보 수정
    @Override
    public void updateUser(HashMap<String, String> map) {
        userMapper.updateUser(map);
    }

    // 회원정보 삭제
    @Override
    public void deleteUser(HashMap<String, String> map) {
        userMapper.deleteUser(map);
    }

}
