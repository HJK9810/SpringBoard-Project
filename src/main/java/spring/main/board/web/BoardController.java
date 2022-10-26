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
        BoardItems item = boardService.oneView(id);

        return new ResponseEntity<BoardItems>(item, HttpStatus.OK);
    }

    @PostMapping("/add")
    private ResponseEntity<BoardItems> createOne(@RequestBody BoardItems item) {
        item = boardService.addItem(item);

        return new ResponseEntity<BoardItems>(item, HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    private ResponseEntity<BoardItems> showforEdit(@PathVariable(name = "id") Long id) {
        BoardItems item = boardService.editView(id);

        return new ResponseEntity<BoardItems>(item, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    private ResponseEntity<BoardItems> editOne(@PathVariable(name = "id") Long id, @RequestBody BoardItems item) {
        item = boardService.editItem(id, item);

        return new ResponseEntity<BoardItems>(item, HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    private ResponseEntity<BoardItems> deletOne(@PathVariable(name = "id") Long id) {
        BoardItems item = boardService.deletItem(id);

        return new ResponseEntity<BoardItems>(item, HttpStatus.OK);
    }
}
