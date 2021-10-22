package com.ryanfinken.PersonalBlog.controllers;

import com.ryanfinken.PersonalBlog.data.PostRepository;
import com.ryanfinken.PersonalBlog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    public PostRepository postRepository;

    @GetMapping
    public String displayPosts(@RequestParam(required = false) Integer postId, Model model) {
        if (postId == null) {
            model.addAttribute("posts", postRepository.findAllByOrderByTimestampDesc());
            return "index";
        } else {
            Optional<Post> result = postRepository.findById(postId);
            Post post = result.get();
            model.addAttribute("post", post);
            return "post";
        }
    }

    @GetMapping("about")
    public String displayAboutMe() {
        return "about";
    }

    @GetMapping("projects")
    public String displayProjects() {
        return "projects";
    }
}
