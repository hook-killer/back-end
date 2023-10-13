package HookKiller.server.board.entity;

import HookKiller.server.board.type.BoardType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "tbl_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;

    @OneToMany(mappedBy = "board")
    private List<Article> article;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @NotNull
    private String description;

}