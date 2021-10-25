package com.ryanfinken.PersonalBlog;

import com.ryanfinken.PersonalBlog.controllers.AuthenticationController;
import com.ryanfinken.PersonalBlog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {
    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> blacklist = Arrays.asList("/admin");

    private static boolean isBlackListed(String path) {
        for (String prefix : blacklist) {
            if (path.startsWith(prefix)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        if (! isBlackListed(request.getRequestURI())) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        if (user != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
