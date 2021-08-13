package com.example.FastCampusWeb03.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor/public")
public class PublicController {//모든 사용자가 사용하는 컨트롤러
    //오픈 API 제공, 권한 필요 없다
    @GetMapping("/hello")
    public String hello(){
        return "public hello";
    }

}
