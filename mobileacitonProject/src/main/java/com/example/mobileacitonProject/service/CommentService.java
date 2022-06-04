package com.example.mobileacitonProject.service;

import com.example.mobileacitonProject.model.Comment;
import com.example.mobileacitonProject.model.User;
import com.example.mobileacitonProject.repository.CommentsRepository;
import com.example.mobileacitonProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService implements ICommentService {

    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public ResponseEntity<Comment> addComment(Long userId, Comment comment) {

        Comment newComment = new Comment();

        newComment.setText(comment.getText());
        newComment.setDate(new Date());
        newComment.setProductId(comment.getProductId());
        newComment.setUser(userService.findById(userId));

       commentsRepository.save(newComment);
       log.info("Comment is saved.");

       return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Comment> update(Comment comment, Long id) {
        Optional<User> user = userRepository.findById(comment.getUser().getUserId());
        if (!user.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Comment> comments = commentsRepository.findById(id);
        if (!comments.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        comment.setUser(user.get());
        comment.setId(user.get().getUserId());
        commentsRepository.save(comment);

        return ResponseEntity.noContent().build();
    }

    @Override
    public List<Comment> findAll(Comment comment) {

        return commentsRepository.findAll();
    }

    @Override
    public ResponseEntity<Comment> getById(Long id) {
        Optional<Comment> comments = commentsRepository.findById(id);
        if (!comments.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(comments.get());
    }

    @Override
    public ResponseEntity<Comment> deleteComment(Long id) {
        Optional<Comment> comments = commentsRepository.findById(id);
        if (!comments.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        commentsRepository.delete(comments.get());

        return ResponseEntity.noContent().build();
    }

}
