package com.example.FastCampusWeb03.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//basePackages로 해당 패키지를 지정할수 있다.
//@ControllerAdvice //View Resolver 기준 사용
public class GlobalControllerAdvice {

    //우선 순위 3
    @ExceptionHandler(value = Exception.class)//전체적인 에러를 다잡는다
    public ResponseEntity exception(Exception e){
        //예외 발생이 이리로 다온다
        System.out.println(e.getClass().getName());//클래스 이름 출력
        System.out.println("-------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("-------------");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }
    //우선 순위 2
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(Exception e){
        //특정 메소드의 예외를 잡기

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }
}
