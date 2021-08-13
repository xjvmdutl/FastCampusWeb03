package com.example.FastCampusWeb03.interceptor.interceptors;

import com.example.FastCampusWeb03.interceptor.annotation.Auth;
import com.example.FastCampusWeb03.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ContentCachingRequestWrapper httpServletRequest =(ContentCachingRequestWrapper)request;
        //Filter에서 cashing Request를 넘겨주게되면 형 변환이 가능하다
        String url = httpServletRequest.getRequestURI();
        URI uri =UriComponentsBuilder.fromUriString(httpServletRequest.getRequestURI())
                .query(httpServletRequest.getQueryString())
                .build()
                .toUri();
        //Handler = 다양한 스프링의 정보를 가지고 있다.(매핑정보 등)
        log.info("request url : {}",url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}",hasAnnotation);
        //나의 서버는 모두 public으로 동작을 하는데
        //단! Auth 권한을 가진 요청에 대해서는 다르게 동작
        if(hasAnnotation){
            //권한체크
            //Auth를 가지고 있다.
            String query = uri.getQuery();
            log.info("query : {}",query);
            //쿼리 내용이 동일하면 통과
            if(query.equals("name=steve")) {
                return true;
            }
            //return false;
            //권한이 없을경우 에러 발생
            throw new AuthException();
        }
        return true;//동작 안한다.
    }
    private boolean checkAnnotation(Object handler,Class clazz){
        //Annotation이 있는지 확인하는 메소드
        //resource javascript, html (리소스일경우)
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        //Annotation 체크
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) //메소드에 Annotation이 붙어있는지 학인
                || null != handlerMethod.getBeanType().getAnnotation(clazz)){//클레스에 Annotation이 있는지 확인
            //Auth annotation이 있을때는 true
            return true;
        }
        return false;

    }
}
