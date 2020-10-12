package com.casestudy.blog.service;


import com.casestudy.blog.model.Post;
import com.casestudy.blog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostService {
    Post getPostById(long id);

    Optional<Post> findForId(Long id);

    Post save(Post post);

    Page<Post> findByUserOrderedByDatePageable(User user, Pageable pageable);

    Page<Post> findAllOrderedByDatePageable(Pageable pageable);

    void delete(Post post);

}
