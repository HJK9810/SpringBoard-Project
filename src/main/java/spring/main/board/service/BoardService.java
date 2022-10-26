package spring.main.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.main.board.domain.BoardItems;

public interface BoardService {
    Page<BoardItems> findAll(Pageable pageable);

    BoardItems oneView(Long id);
    BoardItems addItem(BoardItems item);
    BoardItems editView(Long id);
    BoardItems editItem(Long id, BoardItems item);
    BoardItems deletItem(Long id);

    boolean checkNull(String str);
}
