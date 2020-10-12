package com.casestudy.blog.controller;


import com.casestudy.blog.model.Post;
import com.casestudy.blog.model.User;
import com.casestudy.blog.service.PostService;
import com.casestudy.blog.service.UserService;
import com.casestudy.blog.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
    public String blogForUsername(@PathVariable String username,
                                  @RequestParam(defaultValue = "0") Optional<String> s,
                                  @PageableDefault(value = 5) Pageable pageable,
                                  Model model) {
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Page<Post> posts = postService.findByUserOrderedByDatePageable(user, pageable);
            Pager pager = new Pager(posts);
            model.addAttribute("pager", pager);
            model.addAttribute("user", user);
            return "posts";
        } else {
            return "error";
        }
    }

}
