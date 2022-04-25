package com.hjstudy.book.springboot.web;


import com.hjstudy.book.springboot.domain.posts.Posts;
import com.hjstudy.book.springboot.domain.posts.PostsRepository;
import com.hjstudy.book.springboot.web.dto.PostsSaveRequestDto;
import com.hjstudy.book.springboot.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//  @SpringBootTest : 스프링 + JPA기능까지 한꺼번에 사용, @WebMvcTest의 경우 JPA지원x
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록테스트() throws  Exception{

        //given
        String url = "http://localhost:" + port + "/api/v1/posts";

        String title= "post등록입니다.";
        String content = "post내용도 등록합니다.";
        String author = "접니당~";

        PostsSaveRequestDto requestDto =
        PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();


        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);


    }

    @Test
    public void Posts_수정테스트() throws  Exception{

        //given
        Posts posts = postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .author("작성자~")
                .build());

        Long updateId = posts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";
        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        PostsUpdateRequestDto requestDto =
                PostsUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .content(expectedContent)
                        .build();


        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);


        //when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = postsRepository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(postsList.get(0).getContent()).isEqualTo(expectedContent);



    }

    @Test
    public void BaseTimeEntity_등록() throws  Exception{
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .author("돌돌")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println("[createdDate] = " + posts.getCreatedDate() + "  / [modifiedDate] =" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }
}
