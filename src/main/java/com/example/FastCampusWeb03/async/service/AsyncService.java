package com.example.FastCampusWeb03.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
@Slf4j
public class AsyncService {

    @Async("async-thread")//우리가 지정했던 Thread에서 동작한다
    //public에서만 동작한다.
    //같은 메소드에서 같은 메소드를 동작시키면 타지 않는다
    public CompletableFuture run(){
        //CompletableFuture : 다른쓰레드에서 실행을 시키고 결과를 받는다.
        //여러개의 API를 받아서 결과를 조인해서 보여줄 때 사용한다.

        return new AsyncResult<String>(hello()).completable();
    }

    //@Async//비동기로 동작시킨다는 뜻
    public String hello() {
        for (int i=0;i<10;++i){
            try {
                Thread.sleep(2000);
                log.info("thread sleep ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("finish");
        return "async hello";
    }
}
