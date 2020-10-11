package com.casestudy.blog.service;


import com.casestudy.blog.model.Post;
import com.casestudy.blog.model.User;
import com.casestudy.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface PostService {
    Post getPostById(long id);

    Optional<Post> findForId(Long id);

    Post save(Post post);

    Page<Post> findByUserOrderedByDatePageable(User user, Pageable pageable);

    Page<Post> findAllOrderedByDatePageable(Pageable pageable);

    void delete(Post post);

}
