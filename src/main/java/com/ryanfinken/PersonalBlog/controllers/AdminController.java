package com.ryanfinken.PersonalBlog.controllers;

import com.ryanfinken.PersonalBlog.data.PostRepository;
import com.ryanfinken.PersonalBlog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    public PostRepository posts;

    @GetMapping("create")
    public String displayCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "admin/create";
    }

    @PostMapping("create")
    public String processCreatePostForm(@ModelAttribute Post post) {
        posts.save(post);
        return "index";
    }
}
