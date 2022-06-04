package com.example.mobileacitonProject.service;

import com.example.mobileacitonProject.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICommentService {

    ResponseEntity<?> addComment(Long userId, Comment comment);
    ResponseEntity<Comment> update(Comment comment, Long id);
    ResponseEntity<Comment> deleteComment(Long id);
    ResponseEntity<Comment> getById(Long id);
    List<Comment> findAll(Comment comment);


}


