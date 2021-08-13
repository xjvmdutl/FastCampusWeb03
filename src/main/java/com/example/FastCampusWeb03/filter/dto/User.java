package com.example.FastCampusWeb03.filter.dto;

import lombok.*;

//@Getter//Getter 메소드 만들어 준다
//@Setter//Setter 메소드 만들어 준다
@NoArgsConstructor//기본 생성자를 만들어 준다
@AllArgsConstructor//매개변수를 다받는 생성자를 만들어 준다
@Data//ToString,Equals,HashCode까지 모든것을 다 만들어 준다
public class User {

    private String name;
    private int age;

}
