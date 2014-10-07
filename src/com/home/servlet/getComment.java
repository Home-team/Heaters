package com.home.servlet;

import com.home.dao.JdbcCommentDao;
import com.home.entity.Comment;
import com.home.entity.Heater;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class getComment extends HttpServlet {

    protected String generateJSON(Comment[] target) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        if(target.length > 0) {
            buffer.append(target[0].toString());
            for(int i = 1; i<target.length; i++) {
                buffer.append(", ");
                buffer.append(target[i].toString());
            }
        }
        buffer.append("]");
        return buffer.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JdbcCommentDao commentDao = new JdbcCommentDao();
        resp.getWriter().print(generateJSON(commentDao.find(req.getParameter("heater_id"))));
    }
}
