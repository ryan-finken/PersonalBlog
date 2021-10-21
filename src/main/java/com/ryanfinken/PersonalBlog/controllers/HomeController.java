package com.ryanfinken.PersonalBlog.controllers;

import com.ryanfinken.PersonalBlog.data.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    public PostRepository postRepository;

    @RequestMapping
    public String displayIndex(Model model) {
        model.addAttribute("posts", postRepository.findAllByOrderByTimestampDesc());
        return "index";
    }
}
