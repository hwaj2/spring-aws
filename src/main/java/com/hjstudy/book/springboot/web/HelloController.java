package com.hjstudy.book.springboot.web;

//간단한 api 만들기

import com.hjstudy.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON객체(string)를 반환하는 컨트롤러, @ResponseBody(메소드단위) 클래스형태로 만들어준것
public class HelloController {

    @GetMapping("/hello")  //HTTP GET 요청
    public String hello(){
        return  "hello";
    }

    @GetMapping("/hello/dto")  // @RequestParam : 파라미터를 받아오는 어노테이션(외부에서 넘긴)
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);

    }
}
