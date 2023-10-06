package HookKiller.server.Notice.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.List;

/**
 * orgArticleLanguage : 원본으로 작성된 언어 타입. KOR:한국어, ENG:영어, CHI:중국어, JPN:일본어
 * status : 공지글 상태. WRITING:작성중, PUBLIC:공개상태, HIDING:숨김처리, DELETE:삭제처리
 * isDeleted : 공지글 삭제 여부
 * createdAt : 공지글 생성일
 * createdUser : 공지글 작성 사용자 ID입력.
 * updatedAt : 공지글 정보 업데이트 일자
 * updated_user : 마지막에 수정한 사용자 ID입력.
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Content> content;

    private String orgArticleLanguage;
    private String status;
    private boolean isDeleted;
    private DateTime createdAt;
    private long createdUser;
    private DateTime updatedAt;
    private long updatedUser;
}
