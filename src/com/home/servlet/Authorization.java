package com.home.servlet;

import com.home.dao.JdbcUserDao;
import com.home.dao.MainJdbcDao;
import com.home.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authorization extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        JdbcUserDao userDao = new JdbcUserDao();
        User user = userDao.auth(req.getParameter("login"),req.getParameter("password"));
        if(user != null) {
            resp.addCookie(new Cookie("login",user.getLogin()));
            req.getSession().setAttribute("user",user);
        }
        if(user != null) {
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }
    }
}
