package com.example.FastCampusWeb03.validation.validator;

import com.example.FastCampusWeb03.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth,String> {
    //<우리가 원하는 Annotation,값>
    //해당 어노테이션은 다른 클래스에서 재사용이 가능하다
    private String pattern;//초기화 했을때의 값 받는다

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //yyyyMM
        try {
            LocalDate localDate = LocalDate.parse(value +"01", DateTimeFormatter.ofPattern(this.pattern));
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
