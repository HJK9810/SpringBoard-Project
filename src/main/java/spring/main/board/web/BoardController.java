package spring.main.board.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.main.board.domain.BoardItems;
import spring.main.board.service.BoardService;
import spring.main.board.service.BoardServiceImpl;

import java.util.Date;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {

    @Autowired
    private BoardService boardService = new BoardServiceImpl();

    @GetMapping("/list")
    private ResponseEntity<Page<BoardItems>> showList(Pageable pageable) {
        Page<BoardItems> page = boardService.findAll(pageable);
        return new ResponseEntity<Page<BoardItems>>(page, HttpStatus.OK);
    }

    @GetMapping("/one/{id}")
    private ResponseEntity<BoardItems> showOne(@PathVariable(name = "id") Long id) {
        BoardItems item = boardService.findByID(id).get();

        return new ResponseEntity<BoardItems>(item, HttpStatus.OK);
    }

    @PostMapping("/add")
    private ResponseEntity<BoardItems> createOne(@RequestBody BoardItems item) {
        Date date = new Date();
        item.setDate(date);
        item.setViewCnt(0L);
        item = boardService.saveItem(item);

        return new ResponseEntity<BoardItems>(item, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    private ResponseEntity<BoardItems> editOne(@PathVariable(name = "id") Long id, @RequestBody BoardItems item) {
        BoardItems finalItem = item;
        boardService.findByID(id).ifPresent(change -> {
            if (!change.getTitle().equals(finalItem.getTitle()) && !boardService.checkNull(finalItem.getTitle())) {
                change.setTitle(finalItem.getTitle());
            }
            if (!change.getText().equals(finalItem.getText()) && !boardService.checkNull(finalItem.getText())) {
                change.setText(finalItem.getText());
            }
            boardService.saveItem(change);
        });
        item = boardService.findByID(id).get();
        return new ResponseEntity<BoardItems>(item, HttpStatus.OK);
    }
}
