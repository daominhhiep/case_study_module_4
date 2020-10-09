package com.casestudy.blog.controller;

import com.casestudy.blog.model.Post;
import com.casestudy.blog.service.PostService;
import com.casestudy.blog.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "0") Optional<String> s,
                       @PageableDefault(value = 5) Pageable pageable,
                       Model model) {
        Page<Post> posts = postService.findAllOrderedByDatePageable(pageable);
        Pager pager = new Pager(posts);
        model.addAttribute("pager", pager);
        return "home";
    }

    @GetMapping("/")
    public String home2(@RequestParam(defaultValue = "0") Optional<String> s,
                       @PageableDefault(value = 5) Pageable pageable,
                       Model model) {
        Page<Post> posts = postService.findAllOrderedByDatePageable(pageable);
        Pager pager = new Pager(posts);
        model.addAttribute("pager", pager);
        return "home";
    }
}
