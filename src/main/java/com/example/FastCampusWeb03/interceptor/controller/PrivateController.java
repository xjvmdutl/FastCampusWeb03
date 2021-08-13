package com.example.FastCampusWeb03.interceptor.controller;

import com.example.FastCampusWeb03.interceptor.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Auth
@RestController
@RequestMapping("interceptor/private")
public class PrivateController {

    @RequestMapping("/hello")
    public String hello(){
        //인증이 완료된 사용자만
        log.info("private hello Controller");//해당로그 안찍는다
        return "private hello";
    }

}
