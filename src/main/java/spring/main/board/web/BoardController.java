package spring.main.board.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.main.board.domain.BoardItems;
import spring.main.board.service.BoardService;
import spring.main.board.service.BoardServiceImpl;

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
}
