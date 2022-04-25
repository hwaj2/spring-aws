package com.hjstudy.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*

 # Entity클래스에서는 절대 Setter메소드를 만들지않는다.
  : 해당 필드값의 변경이 필요하면 메소드를 이용해 추가  EX ) setState() X   cancleOrder() O
     Setter가 없는 상황에서 어떻게 값을채워 DB에 삽입?
        1. 생성자를 통해 최종값을 채운뒤 DB삽입
        2. 값 변경이 필요한경우 해당 이벤트에 맞는 public메소드를 호출하여 변경하는것
        3. @Builder통해 제공되는 빌더클래스 사용   ★★

 # Entity클래스를 Request/Response 클래스로 사용하지 않는다.


*/

@Getter //lombok
@NoArgsConstructor //lombok(기본생성자)
@Entity //테이블(jpa)
public class Posts extends BaseTimeEntity{ //실제 DB의 테이블과 매칭될 객체클래스 = Entity클래스

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk생성규칙(IDENTITY = auto increment)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //추가변경옵션시 사용(타입을 TEXT로 변경)
    private String content;

    private String author;

    @Builder //생성자대신 빌더패턴 : 필드의 값을 초기화하는 것은 똑같지만, 어느필드에 어떤값을 채워야할지 명확하고 쉽게 인지가능!!
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}


