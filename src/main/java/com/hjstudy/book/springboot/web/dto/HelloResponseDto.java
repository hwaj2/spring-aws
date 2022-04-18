package com.hjstudy.book.springboot.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //선언된 모든 final필드가 포함된 생성자를 생성(finalX 생성하지X)
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
