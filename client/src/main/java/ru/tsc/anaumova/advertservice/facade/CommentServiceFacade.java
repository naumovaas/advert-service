package ru.tsc.anaumova.advertservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.CommentDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.Comment;
import ru.tsc.anaumova.advertservice.service.CommentService;

@Service
public class CommentServiceFacade {

    private final CommentService commentService;

    private final MapperDto<Comment, CommentDto> commentMapperDto;

    @Autowired
    public CommentServiceFacade(CommentService commentService) {
        this.commentService = commentService;
        this.commentMapperDto = new MapperDto<>(CommentDto.class, Comment.class);
    }

    public void save(CommentDto commentDto) {
        final Comment comment = commentMapperDto.toEntity(commentDto);
        commentService.save(comment);
    }

    public void update(CommentDto commentDto, UserDetails userDetails) throws EntityNotFoundException {
        final Comment comment = commentMapperDto.toEntity(commentDto);
        commentService.update(comment, userDetails);
    }

    public void delete(Long commentId) {
        commentService.delete(commentId);
    }

}
