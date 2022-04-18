package com.hjstudy.book.springboot.dto;

import com.hjstudy.book.springboot.web.dto.HelloResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){

        String name ="hwwajeong";
        int amount = 10000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);

        // assertj(테스트 검증 라이브러리) : junit보다 assertj의 라이브러리가 편리
        // 검증하고싶은 대상 메소드인자로, 체이닝지원, 동등비교메소드(isEqualTo, 같을경우만 성공!)

    }
}
