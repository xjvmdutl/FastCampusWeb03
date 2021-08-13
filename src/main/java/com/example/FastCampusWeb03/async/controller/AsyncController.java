package com.example.FastCampusWeb03.async.controller;

import com.example.FastCampusWeb03.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {
    //@RequiredArgsConstructor 과 같은 동작
    private final AsyncService asyncService;
    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/hello")
    public CompletableFuture hello(){
        /*
        asyncService.hello();
        log.info("method end");
        return "hello";
         */
        log.info("completable future init");
        return asyncService.run();
    }
}
