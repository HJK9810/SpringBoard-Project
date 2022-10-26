package spring.main.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.main.board.domain.BoardItems;
import spring.main.board.repository.BoardRepository;

import java.util.Date;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Page<BoardItems> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public BoardItems oneView(Long id) {
        BoardItems item = boardRepository.findByID(id).get();
        item.setViewCnt(item.getViewCnt() + 1);
        boardRepository.save(item);

        return item;
    }

    @Override
    public BoardItems addItem(BoardItems item) {
        Date date = new Date();
        item.setDate(date);
        item.setViewCnt(0L);
        boardRepository.save(item);

        return item;
    }

    @Override
    public BoardItems editView(Long id) {
        BoardItems item = boardRepository.findByID(id).get();
        item.setViewCnt(item.getViewCnt() - 1);
        boardRepository.save(item);

        return item;
    }

    @Override
    public BoardItems editItem(Long id, BoardItems item) {
        boardRepository.findByID(id).ifPresent(change -> {
            if(!change.getTitle().equals(item.getTitle()) && !checkNull(item.getTitle())) {
                change.setTitle(item.getTitle());
            }
            if (!change.getText().equals(item.getText()) && !checkNull(item.getText())) {
                change.setText(item.getText());
            }
            boardRepository.save(change);
        });

        return boardRepository.findByID(id).get();
    }
    @Override
    public BoardItems deletItem(Long id) {
        BoardItems item = boardRepository.findByID(id).get();
        item.setTitle("Delete Clear!");
        boardRepository.deleteById(id);

        return item;
    }

    @Override
    public boolean checkNull(String str) {
        return str == null || str.isEmpty();
    }
}
