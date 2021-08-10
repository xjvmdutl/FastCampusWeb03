package com.example.FastCampusWeb03.validation.dto;

import com.example.FastCampusWeb03.validation.annotation.YearMonth;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class User {

    @NotBlank //빈값,공백,스페이스도 안되
    private String name;

    @Min(value = 0)//최소 0살 이상이어야되
    @Max(value = 90)
    private int age;
    /*
    @Email //이메일 형식
    private String email;

    //사용자 커스텀 형식(정규식)
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$" , message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;

    @Size(min = 6,max = 6)//yyyymm이 아니면 안된다
    @YearMonth
    private String reqYearMonth;
    */
    @Valid//해당 어노테이션을 꼭 붙혀주어야 된다.
    private List<Car> cars;
    //클래스안에 Validation 할 오브젝트가 있을경우에는 반드시 @Valid를 붙여야된다.

    /*
    @AssertTrue(message = "yyyyMM 의 형식에 맞지 않습니다")
    public boolean isReqYearMonthValidation(){//Boolean을 리턴하면 is가 붙어야 된다
        //return true - 정상 flase - 비정상
        System.out.println("assert true call");
        try {
            LocalDate localDate = LocalDate.parse(this.getReqYearMonth() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        }catch (Exception e){
            return false;
        }
        //코드의 재사용이 불가능하다.
        //어노테이션을 만들어서 재사용이 가능하게 할수 있다

        return true;
    }
    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}
