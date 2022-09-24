package com.szs.account.bu.api.controller;

import com.szs.account.bu.domain.User;
import com.szs.account.bu.api.service.JwtService;
import com.szs.account.bu.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserContorller {
    public static final Logger logger = LoggerFactory.getLogger(UserContorller.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> registUser(@RequestBody Map<String, String> map) throws Exception {
        System.out.println("User controller create 시작");
        User user = User
                .builder()
                .id(map.get("id"))
                .password(map.get("password"))
                .build();
        User registeredUser = userService.save(user);

        HttpStatus state;
        if (registeredUser != null) state = HttpStatus.OK;
        else state = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>("", state);
    }
/*
* @ApiOperation(value = "회원정보삭제", notes = "", response = Map.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(
            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) @PathVariable String id) {
        System.out.println("delete user 호출");
        * */

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable String id) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        User uesr = userService.findById(id);
        userService.delete(uesr);
        resultMap.put("message", SUCCESS);
        status = HttpStatus.ACCEPTED;

        try {
        } catch (Exception e) {
            logger.error("삭제 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

//    token = jwtService.create("id", user.getId(), "token");

//    @PostMapping
//    public ResponseEntity<String> registUser(@RequestBody Map<String, String> map) throws Exception {
//        System.out.println("resister controller 시작");
//        userService.registUser(map);
//        System.out.println("map : " + map);
//        User loginUser = userService.loginUser(map.get("id"), map.get("password"));
//        String token = "";
//        if (loginUser != null) {
//            return new ResponseEntity<String>(token, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<String>(token, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @ApiOperation(value = "회원정보수정", notes = "", response = Map.class)
//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> updateUser(
//            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) @PathVariable String id,
//            @RequestBody Map<String, String> map) {
//        System.out.println("update User 호출");
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            map.put("id", id);
//            userService.updateUser(map);
//            resultMap.put("userInfo", map);
//            resultMap.put("message", SUCCESS);
//            status = HttpStatus.ACCEPTED;
//        } catch (Exception e) {
//            logger.error("수정 실패 : {}", e);
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
//

//
//    @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> loginUser(
//            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
//        System.out.println("login contoller 호출");
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            User loginUser = userService.loginUser(user.getId(), user.getPassword());
//            if (loginUser != null) {
//                String token = jwtService.create("id", loginUser.getId(), "token");// key, data, subject
//                logger.debug("로그인 토큰정보 : {}", token);
//                resultMap.put("token", token);
//                resultMap.put("message", SUCCESS);
//                status = HttpStatus.ACCEPTED;
//            } else {
//                resultMap.put("message", FAIL);
//                status = HttpStatus.ACCEPTED;
//            }
//        } catch (Exception e) {
//            logger.error("로그인 실패 : {}", e);
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Integer> checkPasswordFind(@PathVariable String id, @RequestParam String email) throws Exception {
//        int cnt = 0;
//Optional<User> us = userService.fbp();
//        System.out.println("checkPasswordFind 실행");
//        cnt = userService.checkPasswordFind(id, email);
//        return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
//    @GetMapping("/info/{id}")
//    public ResponseEntity<Map<String, Object>> getUserInfo(
//            @PathVariable("id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
//            HttpServletRequest request) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status;
//        try {
//            User user = userService.infoUser(id);
//            resultMap.put("userInfo", user);
//            resultMap.put("message", SUCCESS);
//            status = HttpStatus.ACCEPTED;
//        } catch (Exception e) {
//            logger.error("정보조회 실패 : {}", e);
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            return new ResponseEntity<Map<String, Object>>(resultMap, status);
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
}
/*
    @GetMapping("/check/{id}")
    public int checkId(@PathVariable String id) throws Exception {
        System.out.println("checkId controller 시작");
        int cnt = 1;
        cnt = userService.checkId(id);
        return cnt;
    }


*
* */