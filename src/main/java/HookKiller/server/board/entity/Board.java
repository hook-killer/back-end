package HookKiller.server.board.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "Board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Article> article;

    @NotNull
    private String name;

    private String boardType;

    @NotNull
    private String description;

}