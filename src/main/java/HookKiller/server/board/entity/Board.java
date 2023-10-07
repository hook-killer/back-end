package HookKiller.server.board.entity;

import HookKiller.server.board.type.BoardType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Article> article;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @NotNull
    private String description;

}