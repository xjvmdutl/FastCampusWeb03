package com.example.FastCampusWeb03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@ServletComponentScan
@EnableAsync//비동기 처리 어노테이션
public class FastCampusWeb03Application {

	public static void main(String[] args) {
		SpringApplication.run(FastCampusWeb03Application.class, args);
	}

}
