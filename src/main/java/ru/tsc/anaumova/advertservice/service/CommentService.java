package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.CommentDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.Mapper;
import ru.tsc.anaumova.advertservice.model.Comment;
import ru.tsc.anaumova.advertservice.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final Mapper<Comment, CommentDto> commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = new Mapper<>(CommentDto.class, Comment.class);
    }

        public void save(CommentDto commentDto) {
            final Comment comment = commentMapper.toEntity(commentDto);
            commentRepository.save(comment);
        }

        public void update(CommentDto commentDto) throws EntityNotFoundException {
            final Comment comment = commentRepository
                    .findById(commentDto.getCommentId())
                    .orElseThrow(EntityNotFoundException::new);
            comment.setText(commentDto.getText());
            commentRepository.save(comment);
        }

        public void delete(Long commentId) {
            commentRepository.deleteById(commentId);
        }

}