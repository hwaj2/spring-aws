package com.hjstudy.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 스프링 부트의 자동설정, 스프링 Bean읽기,생성을 모두 자동으로 설정
// @SpringBootApplication이 있는 위치부터 설정을 읽어나감 -> 항상 프로젝트 최상단에 위치해야함!!!!
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


