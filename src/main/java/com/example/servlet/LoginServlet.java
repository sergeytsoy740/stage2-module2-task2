package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (user == null) { // все варианты работают
//            req.getRequestDispatcher("/login.jsp").forward(req, resp);
//            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
//            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            resp.sendRedirect("/login.jsp");
//            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
//            req.getRequestDispatcher("/user/hello.jsp").forward(req, resp);
//            getServletContext().getRequestDispatcher("/user/hello.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        List<String> users = Users.getInstance().getUsers();
        if (users.contains(login) && !password.isEmpty()) {
            req.getSession().setAttribute("user", login);
            resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }


    }
}
