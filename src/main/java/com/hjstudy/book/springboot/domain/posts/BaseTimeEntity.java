package com.hjstudy.book.springboot.domain.posts;

// 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //jpa entity클래스들이 해당클래스를 상속할경우 필드들을 칼럼으로 인식할수 있도록
@EntityListeners(AuditingEntityListener.class) //해당 클래스에 Auditing기능을 포함
public class BaseTimeEntity {

    //Entity가 생성되어 저장될때 시간이 자동저장
    @CreatedDate
    private LocalDateTime createdDate;


    //조회한 Entity의 값을 변경할때 시간이 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
