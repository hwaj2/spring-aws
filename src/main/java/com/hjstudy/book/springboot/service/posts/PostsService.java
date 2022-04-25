package com.hjstudy.book.springboot.service.posts;


import com.hjstudy.book.springboot.domain.posts.Posts;
import com.hjstudy.book.springboot.domain.posts.PostsRepository;
import com.hjstudy.book.springboot.web.dto.PostsListResponseDto;
import com.hjstudy.book.springboot.web.dto.PostsResponseDto;
import com.hjstudy.book.springboot.web.dto.PostsSaveRequestDto;
import com.hjstudy.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {


    private final PostsRepository postsRepository;


    /**
     * 게시글 저장
     * @param requestDto
     * @return
     */
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
       return postsRepository.save(requestDto.toEntity()).getId();

    }


    /**
     * 게시글 수정
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){

        // <Optional<Posts>을 이용해 구현하는것보다 체이닝을 통해 간결하게 개발
        Posts posts
                = postsRepository.findById(id).orElseThrow(() -> new
                        IllegalArgumentException("해당게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    /**
     * 게시글 조회
     * @param id
     * @return8
     */
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new
                IllegalArgumentException("해당게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    /**
     * 게시글 전체조회(ALL)
     * @return
     */
    @Transactional(readOnly = true) //트랜잭션범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선(등록/수정/삭제기능이 전혀없는 메소드사용 권장)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // ==  .map(posts -> () new PostsListResponseDto(posts))
                .collect(Collectors.toList());
        //결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 변환하는 메소드
    }



    /**
     * 게시글 삭제
     * @param id
     */
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
    }

}
