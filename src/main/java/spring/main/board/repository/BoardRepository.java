package spring.main.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.main.board.domain.BoardItems;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardItems, Long> {
    Optional<BoardItems> findByID(Long id);

    Page<BoardItems> findAll(Pageable pageable);

    List<BoardItems> findAllByID(Long id);
}
