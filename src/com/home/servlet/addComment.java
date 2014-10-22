package com.home.servlet;

import com.home.dao.JdbcCommentDao;
import com.home.dao.JdbcHeaterDao;
import com.home.entity.Comment;
import com.home.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class addComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") == null) {
            resp.getWriter().print("false");
            return;
        }

        String text = req.getParameter("comment");
        System.out.println(text);
        java.util.Date d = new java.util.Date();
        if(text.length() <= 200) {
            Comment comment = new Comment();
            comment.setName(((User)req.getSession().getAttribute("user")).getLogin());
            comment.setText(text);
            comment.setData(new Date(d.getTime()));
            comment.setHeater_id(req.getParameter("heater_id"));

            JdbcCommentDao commentDao = new JdbcCommentDao();
            commentDao.create(comment);
        }
    }
}
