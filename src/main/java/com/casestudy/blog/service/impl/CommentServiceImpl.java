package com.casestudy.blog.service.impl;

import com.casestudy.blog.model.Comment;
import com.casestudy.blog.repository.CommentRepository;
import com.casestudy.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
