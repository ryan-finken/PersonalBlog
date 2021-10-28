package com.ryanfinken.PersonalBlog.controllers;

import com.ryanfinken.PersonalBlog.data.PostRepository;
import com.ryanfinken.PersonalBlog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    public PostRepository postRepository;

    @GetMapping
    public String displayAdminIndex() {
        return "admin/index";
    }

    @GetMapping("create")
    public String displayCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "admin/create";
    }

    @PostMapping("create")
    public String processCreatePostForm(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("delete")
    public String displayDeletePostsForm(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "admin/delete";
    }

    @PostMapping("delete")
    public String processDeletePostsForm(@RequestParam(required = false) int[] postIds) {
        if (postIds != null) {
            for (int id : postIds) {
                postRepository.deleteById(id);
            }
        }

        return "redirect:/";
    }
}
