package com.example.FastCampusWeb03.exception.controller;

import com.example.FastCampusWeb03.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exception")
public class ExceptionApiController {
    @RequestMapping("/user")//required - false로 없어도 동작 되게 한다(기본값은 true이다)
    public User get(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        //null포인터 에러가 난다(500에러)
        int a = 10 + age;
        return user;
    }

    @PostMapping("/user")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        //클라이언트는 400에러임만을 보여준다.//메세지를 못본다.

        return user;
    }

    //해당 Exception은 컨트롤러 안의 에러만 잡는다.
    //우선순위 1
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(Exception e){
        System.out.println("api controller");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }
}
