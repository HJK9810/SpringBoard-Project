package spring.main.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.main.board.domain.BoardItems;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Page<BoardItems> findAll(Pageable pageable);

    BoardItems oneView(Long id);
    void saveItem(BoardItems item);
    BoardItems deletItem(Long id);

    boolean checkNull(String str);
}
