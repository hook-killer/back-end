package HookKiller.server.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * id : PK
 * name :게시판 명
 * boardType : 게시판 종류
 * description : 게시판 사용 용도
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Article> article;

    private String name;
    private String boardType;
    private String description;

}