package com.casestudy.blog.service;

import com.casestudy.blog.model.Like;
import com.casestudy.blog.model.Post;
import com.casestudy.blog.model.User;

public interface LikeService {
    Iterable<Like> findAllByPost(Post post);

    Iterable<Like> findAllByUser(User user);

    void save(Like like);

    void remove(Like like);

    boolean existsByUserAndPost(User user, Post post);

    Long countAllByPost(Post post);

    Like getByUserAndPost(User currentUser, Post currentPost);
}
