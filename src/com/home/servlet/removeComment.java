package com.home.servlet;

import com.home.dao.JdbcCommentDao;
import com.home.entity.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class removeComment extends HttpServlet {
    // /removeComment?id=1
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        JdbcCommentDao commentDao = new JdbcCommentDao();
        Comment comment = new Comment();
        comment.setId(req.getParameter("id"));
        commentDao.remove(comment);
    }
}
