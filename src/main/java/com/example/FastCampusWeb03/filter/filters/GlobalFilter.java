package com.example.FastCampusWeb03.filter.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@Component//필터가 등록되어야 되므로 Component 등록
//@WebFilter(urlPatterns = "/filter/user/*")//특정 URL 지정
public class GlobalFilter implements Filter {//Javax Servlet의 Filter를 상속

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리
        //필터딴에서는 request,response를 변경해 줄수 있다.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);//형변환후 매개변수로 넘겨줘야된다
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);
        //해당 변수 타입으로 변경하여 Cashing을 할수있다.
        //아래 문제를 해결 가능(이미 Cashing이 되어있기 때문에, 몇번이도 읽을수 있게 해준다.)
        //길이만 초기화를 시키고 실제 컨텐츠 내용이 들어가지 않기 떄문에 에러 발생
        //doFilter를 통해서 실제 내부 스프링에 들어가야 메소드가 실행되서 request 내용이 컨텐츠에 들어간다.
        /*
            //해당부분은 한번밖에 못읽는데 Filter에서 읽었다
            BufferedReader br = httpServletRequest.getReader();
            br.lines().forEach(line->{
                log.info("url : {}, line : {}",url,line);
            });
            //BufferedReader는 커서 단위로 읽는다 -> Filter에서 Body를 끝까지 읽었다.
            //Post메핑된 곳에서 Body를 읽을 부분이 없기때문에 에러(Filter에서 읽기 때문에)
        */
        chain.doFilter(httpServletRequest,httpServletResponse);
        //전송시 새로 생성한 request,response를 전달
        String url = httpServletRequest.getRequestURI();
        //후처리
        //request
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("response url : {},responseBody : {}",url,reqContent);
        String resContent = new String(httpServletResponse.getContentAsByteArray());
        //Body에 내용이 비어있다.(Body에 커서가 끝까지 내려갔기 떄문에)
        //내가 읽었던 내용만큼 복사를 해주어야된다.
        int httpStatus = httpServletResponse.getStatus();
        httpServletResponse.copyBodyToResponse();//내가 읽었던 내용을 복사,다시 한번 Body내용을 채워준다
        log.info("response status : {},responseBody : {}",httpStatus,resContent);
    }
}
