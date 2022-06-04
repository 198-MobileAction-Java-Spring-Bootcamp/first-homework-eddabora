package com.example.mobileacitonProject.controller;

import com.example.mobileacitonProject.model.Comment;
import com.example.mobileacitonProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/{userId}/addComments")
    public ResponseEntity<?> addComment(@PathVariable Long userId,
                                              @RequestBody @Valid Comment comment) {
        commentService.addComment(userId,comment);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Comment> update(@RequestBody @Valid Comment comment, @PathVariable Long id) {
        commentService.update(comment, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getById(@PathVariable Long id) {
        commentService.getById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public List<Comment> findAll(@RequestBody @Valid Comment comment) {

        return commentService.findAll(comment);
    }


}
