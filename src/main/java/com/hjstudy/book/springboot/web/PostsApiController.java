package com.hjstudy.book.springboot.web;


import com.hjstudy.book.springboot.service.posts.PostsService;
import com.hjstudy.book.springboot.web.dto.PostsResponseDto;
import com.hjstudy.book.springboot.web.dto.PostsSaveRequestDto;
import com.hjstudy.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 스프링 빈을 주입받는 방식의 3가지중 생성자주입방식을 권장,
// 의존성관계 변할때마다 생성자를 수정해야되기때문에 lombok을 통해 생성자를 생성

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /*
    * REST에서 CRUD- HTTP Method
    * 생성(Create) - POST
    * 읽기(Read) - GET
    * 수정(Update) - PUT
    * 삭제(Delete) - DELETE
    * */

    /**
     * 게시글 등록
     * @param requestDto
     * @return
     */
    @PostMapping("/api/v1/posts")
    //@RequestBody : HTTP요청의 바디 내용(json형태)을 통째로 자바 객채로 변환해서 매핑된 파라미터로 전달
    public Long save(@RequestBody PostsSaveRequestDto requestDto){;
        return postsService.save(requestDto);
    }


    /**
     * 게시글 수정
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return  postsService.update(id, requestDto);

    }

    /**
     * 게시글 조회
     * @param id
     * @return
     */
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    /**
     * 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/api/v1/posts/{id}")
    public Long postsDelete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }


}




