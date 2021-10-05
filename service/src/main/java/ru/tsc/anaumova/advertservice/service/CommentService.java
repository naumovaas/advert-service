package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.model.Comment;
import ru.tsc.anaumova.advertservice.repository.CommentRepository;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void save(Comment comment) {
        comment.setDate(new Timestamp(new Date().getTime()));
        commentRepository.save(comment);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(Comment comment) throws EntityNotFoundException {
        final Comment commentFromDb = commentRepository
                .findById(comment.getCommentId())
                .orElseThrow(EntityNotFoundException::new);
        commentFromDb.setText(comment.getText());
        commentRepository.save(commentFromDb);
    }


    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}