package HookKiller.server.board.service;

import HookKiller.server.board.dto.BoardDto;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
//        boardDtoList = boardList.stream().map().toList();
        return boardDtoList;
    }
}
