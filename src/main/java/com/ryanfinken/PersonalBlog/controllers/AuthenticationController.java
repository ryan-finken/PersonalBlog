package com.ryanfinken.PersonalBlog.controllers;

import com.ryanfinken.PersonalBlog.data.UserRepository;
import com.ryanfinken.PersonalBlog.models.User;
import com.ryanfinken.PersonalBlog.models.dto.LoginFormDTO;
import com.ryanfinken.PersonalBlog.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey= "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute("loginFormDTO", new LoginFormDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute LoginFormDTO loginFormDTO,
                                   HttpServletRequest request,
                                   Model model) {
        User user = userRepository.findByUsername(loginFormDTO.getUsername());

        if (user == null) {
            return "redirect:/login";
        }

        if (! user.isMatchingPassword(loginFormDTO.getPassword())) {
            return "redirect:/login";
        }

        setUserInSession(request.getSession(), user);

        return "redirect:/";
    }

    @GetMapping("register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute("registerFormDTO", new RegisterFormDTO());
        return "register";
    }

    @PostMapping("register")
    public String processRegistrationForm(@ModelAttribute RegisterFormDTO registerFormDTO,
                                          HttpServletRequest request) {
        if (
                userRepository.findByUsername(registerFormDTO.getUsername()) != null ||
                       ! registerFormDTO.getPassword().equals(registerFormDTO.getVerifyPassword())
        ) {
            return "redirect:/register";
        }

        User user = new User(registerFormDTO.getUsername(),registerFormDTO.getPassword());
        userRepository.save(user);

        setUserInSession(request.getSession(), user);

        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
