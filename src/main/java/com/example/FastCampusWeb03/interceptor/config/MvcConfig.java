package com.example.FastCampusWeb03.interceptor.config;

import com.example.FastCampusWeb03.interceptor.interceptors.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor//final에서 생성된 객체를 생성자에서 주입 받을수 있다.
public class MvcConfig implements WebMvcConfigurer {

    //@Autowired //스프링에서는 이렇게 받을수 있지만 순환참조가 발생할수가 있다.
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //exclude로 뺼수도 있다
        registry.addInterceptor(authInterceptor).addPathPatterns("/interceptor/private/*");//특정 URL을 지정할수 있다
        //인터셉터가 여러개일 경우 순차적으로 타게된다
    }
}
