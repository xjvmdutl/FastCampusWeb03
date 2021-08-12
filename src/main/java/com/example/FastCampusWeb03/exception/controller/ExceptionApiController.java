package com.example.FastCampusWeb03.exception.controller;

import com.example.FastCampusWeb03.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/exception")
@Validated
public class ExceptionApiController {
    @RequestMapping("/user")//required - false로 없어도 동작 되게 한다(기본값은 true이다)
    public User get(
            @Size(min = 2)
            @RequestParam String name,

            @NotNull
            @Min(1)
            @RequestParam Integer age){
        //@Validated 어노테이션을 붙히게 되면 RequestParam도 Validation이 가능하다.

        User user = new User();
        user.setName(name);
        user.setAge(age);

        return user;
    }

    @PostMapping("/user")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        //클라이언트는 400에러임만을 보여준다.//메세지를 못본다.

        return user;
    }
    /*
    //해당 Exception은 컨트롤러 안의 에러만 잡는다.
    //우선순위 1
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(Exception e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }
    */

}
