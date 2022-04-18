package com.hjstudy.book.springboot.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // 단위테스트가 끝날때마다 수행됟는 메소드를 지정
    // 테스트간의 데이터침범을 막기위해, 데이터가남아있을경우 다음테스트에 영향
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){

        //given
        String title = "jpa테스트글쓰기";
        String content = "jpa본문입니다.";

        postsRepository.save(Posts.builder()  //save() : id값이 있으면 update, 없으면 insert
                .title(title)
                .content(content)
                .author("shinh3222@naver.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();  //findAll() : 모든데이터를 조회해주는 쿼리


        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
