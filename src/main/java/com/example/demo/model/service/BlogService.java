package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;
import com.example.demo.model.repository.BlogRepository;
import com.example.demo.model.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 

public class BlogService {
    @Autowired
    // BoardRepository boardRepository; 

    //private final BlogRepository blogRepository;
    // private final BoardRepository blogRepository; // 리포지토리 선언 
    private final BoardRepository boardRepository; // 리포지토리 선언


    // public List<Article> findAll() { 
    //     return blogRepository.findAll();
    // }
    // public List<Board> findAll() { // 게시판 전체 목록 조회
    //     return blogRepository.findAll();
    // }
    public List<Board> findAll() { // 게시판 전체 목록 조회
        return boardRepository.findAll();
    }

    // public Article save(AddArticleRequest request){
    //     // DTO가 없는 경우 이곳에 직접 구현 가능
    //     // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
    //     // Article article = Article.builder()
    //     // .title(title)
    //     // .content(content)
    //     // .build();
    //     return blogRepository.save(request.toEntity());
    // }
    // public Board save(AddArticleRequest request){
    //     // DTO가 없는 경우 이곳에 직접 구현 가능
    //     return blogRepository.save(request.toEntity());
    // }
    public Board save(AddArticleRequest request){
        return boardRepository.save(request.toEntity());
    }

    // public Optional<Article> findById(Long id) { // 게시판 특정 글 조회
    //     return blogRepository.findById(id);
    // }
    // public Optional<Board> findById(Long id) { // 게시판 특정 글 조회
    //     return blogRepository.findById(id);
    // }        
    public Optional<Board> findById(Long id) { // 게시판 특정 글 조회
        return boardRepository.findById(id);
    }
        

    // public void update(Long id, AddArticleRequest request) {
    //     Optional<Article> optionalArticle = blogRepository.findById(id); // 단일 글 조회
    //     optionalArticle.ifPresent(article -> { // 값이 있으면
    //         article.update(request.getTitle(), request.getContent()); // 값을 수정
    //         blogRepository.save(article); // Article 객체에 저장
    //     });
    // }
    // public void update(Long id, AddArticleRequest request) {
    //     Optional<Board> optionalBoard = blogRepository.findById(id); // 단일 글 조회
    //     optionalBoard.ifPresent(board -> { // 값이 있으면
    //         board.update(request.getTitle(), request.getContent(), request.getUser(), request.getNewdate(), request.getCount(), request.getLikec()); // 값을 수정
    //         blogRepository.save(board); // Article 객체에 저장
    //     });
    // }
    public void update(Long id, AddArticleRequest request) {
        Optional<Board> optionalBoard = boardRepository.findById(id); // 단일 글 조회
        optionalBoard.ifPresent(board -> { // 값이 있으면
            board.update(request.getTitle(), request.getContent(), board.getUser(), board.getNewdate(), board.getCount(), board.getLikec()); // 값을 수정
            boardRepository.save(board); // Article 객체에 저장
        });
    }

    // public void delete(Long id) {
    //     blogRepository.deleteById(id);
    // }
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