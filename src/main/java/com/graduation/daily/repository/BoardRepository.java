package com.graduation.daily.repository;

import com.graduation.daily.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title, String content);

    Page<Board> findByTitleContainingOrContentContainingOrderByIdDesc(String title, String content, Pageable pageable);
}
