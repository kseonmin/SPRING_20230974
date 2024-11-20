package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Board;
import com.example.demo.model.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 

public class BlogService {
    @Autowired
    private final BoardRepository boardRepository; // 리포지토리 선언

    public List<Board> findAll() { // 게시판 전체 목록 조회
        return boardRepository.findAll();
    }

    public Board save(AddArticleRequest request){
        return boardRepository.save(request.toEntity());
    }
       
    public Optional<Board> findById(Long id) { // 게시판 특정 글 조회
        return boardRepository.findById(id);
    }

    public void update(Long id, AddArticleRequest request) {
        Optional<Board> optionalBoard = boardRepository.findById(id); // 단일 글 조회
        optionalBoard.ifPresent(board -> { // 값이 있으면
            board.update(request.getTitle(), request.getContent(), board.getUser(), board.getNewdate(), board.getCount(), board.getLikec()); // 값을 수정
            boardRepository.save(board); // Article 객체에 저장
        });
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
    
    public Page<Board> searchByKeyword(String keyword, Pageable pageable) {
        return boardRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    } // LIKE 검색 제공(대소문자 무시)
    
}