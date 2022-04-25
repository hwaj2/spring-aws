package com.hjstudy.book.springboot;

import com.hjstudy.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class) //스프링부트테스트와 Junit사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class, secure = false)
//web집중하는 어노테이션 (컨트롤러만사용가능) @Contorller, @ControllerAdvice, JPA기능작동X
public class HelloControllerTest {

    @Autowired //스프링이 빈주입
    private MockMvc mvc; //스프링MVC의 시작점, HTTP(GET,POST)대한 WEB API 테스트가능,

    @Test
    public void hello_Test() throws Exception {

        String hello= "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //헤더의 상태, HTTP CODE(200-OK)
                .andExpect(content().string(hello)); //응답의 본문검증

    }


    @Test
    public void helloDto_Test() throws Exception{

        String name ="hwwajeong";
        int amount = 10000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) //요청 파라미터 설정(스트링값만 허용, 숫자,날짜도 문자로변환)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))   // jsonPath : 응답값을 필드별로 검증할수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}


