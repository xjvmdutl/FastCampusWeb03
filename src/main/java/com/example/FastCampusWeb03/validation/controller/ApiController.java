package com.example.FastCampusWeb03.validation.controller;

import com.example.FastCampusWeb03.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){//Validation에 대한 결과가 bindingResult에 들어가게 된다.
        //Validation을 원하는 객체 앞에 @Valid를 붙여주어야한다
        if(bindingResult.hasErrors()){//bindingResult가 에러를 가지고 있냐?
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;//어떤 필드에서 에러가 났는지?
                String message = objectError.getDefaultMessage();
                System.out.println("field : " + field.getField());
                System.out.println(message);
                sb.append("field : "+ field.getField() + "\n");
                sb.append("message : "+message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        //logic 시작
        System.out.println(user);
        //정상적으로 오지만 원하는 형식으로 오지 않는다.
        //옛날식 코드
        //if(user.getPhoneNumber() == "xxx-xxxx-xxxx"){
        //  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        //}
        //if(user.getAge() > 90){
        // 잘못된 패턴일 경우
        //이러한 validation코드가 늘어날수록 코드가 길어지고 확장성이 떨어진다
        //  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        //}
        return ResponseEntity.ok(user);
    }
}
