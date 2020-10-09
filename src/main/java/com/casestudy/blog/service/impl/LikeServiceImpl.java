package com.casestudy.blog.service.impl;

import com.casestudy.blog.model.Like;
import com.casestudy.blog.model.Post;
import com.casestudy.blog.model.User;
import com.casestudy.blog.repository.LikeRepository;
import com.casestudy.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;


    @Override
    public Iterable<Like> findAllByPost(Post post) {
        return likeRepository.findAllByPost(post);
    }

    @Override
    public Iterable<Like> findAllByUser(User user) {
        return likeRepository.findAllByUser(user);
    }

    @Override
    public void save(Like like) {
        likeRepository.save(like);
    }

    @Override
    public void remove(Like like) {
        likeRepository.delete(like);
    }

    @Override
    public boolean existsByUserAndPost(User user, Post post) {
        return likeRepository.existsByUserAndPost(user, post);
    }


    @Override
    public Long countAllByPost(Post post) {
        return likeRepository.countAllByPost(post);
    }

    @Override
    public Like getByUserAndPost(User currentUser, Post currentPost) {
        return likeRepository.findByUserAndPost(currentUser, currentPost);
    }
}
