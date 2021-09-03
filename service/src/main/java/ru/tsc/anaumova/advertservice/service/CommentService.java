package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.CommentDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.mapper.MapperJson;
import ru.tsc.anaumova.advertservice.model.Comment;
import ru.tsc.anaumova.advertservice.repository.CommentRepository;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final MapperDto<Comment, CommentDto> commentMapperDto;

    private final MapperJson<CommentDto> commentDtoMapperJson;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.commentMapperDto = new MapperDto<>(CommentDto.class, Comment.class);
        this.commentDtoMapperJson = new MapperJson<>(CommentDto.class);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void save(String jsonString) {
        final CommentDto commentDto = commentDtoMapperJson.toEntity(jsonString);
        final Comment comment = commentMapperDto.toEntity(commentDto);
        comment.setDate(new Timestamp(new Date().getTime()));
        commentRepository.save(comment);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(String jsonString) throws EntityNotFoundException {
        final CommentDto commentDto = commentDtoMapperJson.toEntity(jsonString);
        final Comment comment = commentRepository
                .findById(commentDto.getCommentId())
                .orElseThrow(EntityNotFoundException::new);
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
    }


    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}