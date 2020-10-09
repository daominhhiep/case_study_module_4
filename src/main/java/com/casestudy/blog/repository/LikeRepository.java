package com.casestudy.blog.repository;

import com.casestudy.blog.model.Like;
import com.casestudy.blog.model.Post;
import com.casestudy.blog.model.User;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
    Iterable<Like> findAllByPost(Post post);

    Iterable<Like> findAllByUser(User user);

    boolean existsByUserAndPost(User user, Post post);

    Long countAllByPost(Post post);

    Like findByUserAndPost(User user, Post post);
}
