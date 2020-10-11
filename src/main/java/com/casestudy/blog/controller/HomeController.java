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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

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

    @ModelAttribute("user")
    private User getPrincipal() {
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            user= userService.findByUsername(((UserDetails) principal).getUsername()).orElse(null);
        }
        return user;
    }
}
