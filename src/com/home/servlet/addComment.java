package com.home.servlet;

import com.home.dao.JdbcCommentDao;
import com.home.dao.JdbcHeaterDao;
import com.home.entity.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class addComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String text = req.getParameter("text");
        java.util.Date d = new java.util.Date();
        if(name.length() >= 3 && text.length() >= 10 && text.length() <= 200) {
            Comment comment = new Comment();
            comment.setName(name);
            comment.setText(text);
            comment.setData(new Date(d.getTime()));
            comment.setHeater_id(req.getParameter("heater_id"));

            JdbcCommentDao commentDao = new JdbcCommentDao();
            commentDao.create(comment);
        }
    }
}
