package com.example.FastCampusWeb03.filter.controller;

import com.example.FastCampusWeb03.filter.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/filter/temp")
public class FilterApiUserController {
//임의의 클래스
    @PostMapping("")
    public User user(@RequestBody User user){
        log.info("User : {} , {}",user,user);
        return user;
    }

}
