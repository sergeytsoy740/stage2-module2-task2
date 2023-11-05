package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) req).getSession(false);
        String user = (String) session.getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            if (chain != null) {
                chain.doFilter(req, resp);
            }
        }
    }
}