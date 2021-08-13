package com.example.FastCampusWeb03.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {
    //쓰레드를 만들어서 설정할수 있다.
    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        threadPoolTaskExecutor.setMaxPoolSize(100);//쓰레드 설정
        threadPoolTaskExecutor.setCorePoolSize(10);//10개를 다쓰면 que에 찬다.
        threadPoolTaskExecutor.setQueueCapacity(10);//que까지 다차게 되면 poolsize가 증가한다

        threadPoolTaskExecutor.setThreadNamePrefix("Async");
        return threadPoolTaskExecutor;
    }
}
