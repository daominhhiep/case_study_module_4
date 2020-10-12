package com.casestudy.blog.controller;


import com.casestudy.blog.model.Like;
import com.casestudy.blog.model.Post;
import com.casestudy.blog.model.User;
import com.casestudy.blog.service.LikeService;
import com.casestudy.blog.service.PostService;
import com.casestudy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;


    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPost(Principal principal,
                          Model model) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isPresent()) {
            Post post = new Post();
            post.setUser(user.get());
            model.addAttribute("post", post);
            return "postForm";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String createNewPost(@Valid Post post,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "postForm";
        } else {
            postService.save(post);
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id,
                                 Principal principal,
                                 Model model) {
        Optional<Post> optionalPost = postService.findForId(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("post", post);
                return "postForm";
            } else {
                return "403";
            }
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPostWithId(@PathVariable Long id,
                                Principal principal,
                                Model model) {
        Optional<Post> optionalPost = postService.findForId(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("username", principal.getName());
            }
            return "post";

        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
    public String deletePostWithId(@PathVariable Long id,
                                   Principal principal) {
        Optional<Post> optionalPost = postService.findForId(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (isPrincipalOwnerOfPost(principal, post)) {
                postService.delete(post);
                return "redirect:/home";
            } else {
                return "403";
            }
        } else {
            return "error";
        }
    }
    private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getUser().getUsername());
    }


    @PostMapping(value = "/post/like")
    public ResponseEntity<Post> like(@RequestBody Like like) {
        Post currentPost = postService.getPostById(like.getPost().getId());
        User currentUser = getPrincipal();
        if (currentUser != null && !likeService.existsByUserAndPost(currentUser, currentPost)) {
            likeService.save(like);
        } else {
            Like currentLike = likeService.getByUserAndPost(currentUser, currentPost);
            likeService.remove(currentLike);
        }
        Long countLike = likeService.countAllByPost(currentPost);
        currentPost.setLikeCount(countLike);
        postService.save(currentPost);
        return new ResponseEntity<>(currentPost, HttpStatus.OK);
    }

    @ModelAttribute("user")
    private User getPrincipal() {
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            user = userService.findByUsername(((UserDetails) principal).getUsername()).orElse(null);
        }
        return user;
    }
}
