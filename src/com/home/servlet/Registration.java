package com.home.servlet;

import com.home.dao.JdbcUserDao;
import com.home.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration extends HttpServlet {
    private boolean notNull(String target) {
        if(target != null) {
            if(!target.equals("null")) {
                return true;
            }
        }
        return false;
    }

    private boolean validation(HttpServletRequest req) {
        if(!notNull(req.getParameter("login"))) {
            return false;
        }

        if(!notNull(req.getParameter("password"))) {
            return false;
        }

        if(!notNull(req.getParameter("name"))) {
            return false;
        }

        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JdbcUserDao userDao = new JdbcUserDao();
        if(validation(req)) {
            User newUser = new User();
            newUser.setName(req.getParameter("name"));
            newUser.setPassword(req.getParameter("password"));
            newUser.setLogin(req.getParameter("login"));
            userDao.create(newUser);
        } else {
            resp.getWriter().print("Error!");
        }
    }
}
