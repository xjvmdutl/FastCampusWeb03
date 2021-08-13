package com.example.FastCampusWeb03.filter.controller;

import com.example.FastCampusWeb03.filter.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j//LogBack사용,Lombok사용시 사용가능, j는 JAVA를 위한거라는 뜻
//log사용가능
@RestController
@RequestMapping("/filter/user")
public class FilterApiController {

    @PostMapping("")
    public User user(@RequestBody User user){
        log.info("User : {} , {}",user,user);//{}에 user.toString()이 들어간다.
        //콘솔에 더이상 System.out.println을 할 필요가 없다.
        return user;
    }

}
