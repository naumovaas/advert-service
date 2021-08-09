package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.CommentDto;
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

}