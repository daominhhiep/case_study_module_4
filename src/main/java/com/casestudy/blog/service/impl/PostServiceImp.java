package com.casestudy.blog.service.impl;

import com.casestudy.blog.model.Post;
import com.casestudy.blog.model.User;
import com.casestudy.blog.repository.PostRepository;
import com.casestudy.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Optional<Post> findForId(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public Page<Post> findByUserOrderedByDatePageable(User user, Pageable pageable) {
        return postRepository.findByUserOrderByCreateDateDesc(user, pageable);
    }

    @Override
    public Page<Post> findAllOrderedByDatePageable(Pageable pageable) {
        return postRepository.findAllByOrderByCreateDateDesc(pageable);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
