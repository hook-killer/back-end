package HookKiller.server.board.controller;

import HookKiller.server.board.dto.BoardDto;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardDto> getBoardList() {
         List<BoardDto> dto = boardService.getBoardList();
         return dto;
    }
}
