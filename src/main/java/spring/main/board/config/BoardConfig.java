package spring.main.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.main.board.service.BoardService;
import spring.main.board.service.BoardServiceImpl;
import spring.main.board.web.BoardController;

@Configuration
public class BoardConfig {
    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl();
    }

    @Bean
    public BoardController boardController() {
        return new BoardController();
    }
}
