package com.example.FastCampusWeb03.validation.dto;

import javax.validation.constraints.*;

public class User {
    @NotBlank //빈값,공백,스페이스도 안되
    private String name;
    
    @Min(value = 0)//최소 0살 이상이어야되
    @Max(value = 90)
    private int age;

    @Email //이메일 형식
    private String email;

    //사용자 커스텀 형식(정규식)
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$" , message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
