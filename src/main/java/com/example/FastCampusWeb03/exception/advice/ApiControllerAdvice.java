package com.example.FastCampusWeb03.exception.advice;

import com.example.FastCampusWeb03.exception.controller.ExceptionApiController;
import com.example.FastCampusWeb03.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import com.example.FastCampusWeb03.exception.dto.Error;

@RestControllerAdvice(basePackageClasses = ExceptionApiController.class)//basePackages로 해당 패키지를 지정할수 있다.
//@ControllerAdvice //View Resolver 기준 사용
public class ApiControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName());//클래스 이름 출력
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    //클라이언트에서 발생한 에러들
    //어떻게 처리 해야되나??
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest){
        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error ->{
            FieldError field = (FieldError) error;
            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            System.out.println("-----------------------------");
            System.out.println(fieldName);
            System.out.println(message);
            System.out.println(value);

            Error errorMessage = new Error();
            errorMessage.setField(fieldName);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(value);
            errorList.add(errorMessage);

        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e,HttpServletRequest httpServletRequest){
        //어떤 필드가 잘못된 건지 정보를 가지고 있다.
        //파라미터의 Validation이 잘못 되었을때

        List<Error> errorList = new ArrayList<>();

        e.getConstraintViolations().forEach(error->{
            //특정 필드 이름 찾기
            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(),false);
            List<Path.Node> list = stream.collect(Collectors.toList());
            String field = list.get(list.size()-1).getName();//마지막 필드에 있어서
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            System.out.println("-----------------------------");
            System.out.println(field);
            System.out.println(message);
            System.out.println(invalidValue);

            Error errorMessage = new Error();
            errorMessage.setField(field);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(invalidValue);
            errorList.add(errorMessage);

        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e,HttpServletRequest httpServletRequest){

        List<Error> errorList = new ArrayList<>();

        //빈값 보낼때의 에러
        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        //String invalidValue = e.getMessage();
        System.out.println(fieldName);
        System.out.println(fieldType);
        //System.out.println(invalidValue);

        Error errorMessage = new Error();
        errorMessage.setField(fieldName);
        errorMessage.setMessage(e.getMessage());
        //errorMessage.setInvalidValue(invalidValue);
        errorList.add(errorMessage);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        //클라이언트가 해당 메시지만 보더라도 무슨 에러인지 알수 있다.
    }
}
