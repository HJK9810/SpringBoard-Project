package spring.main.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.main.board.domain.BoardItems;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Page<BoardItems> findAll(Pageable pageable);
    Optional<BoardItems> findByID(Long id);
    List<BoardItems> findAllByID(Long id);

    BoardItems saveItem(BoardItems item);

    boolean checkNull(String str);
}
