package com.home.servlet;

import com.home.dao.JdbcHeaterDao;
import com.home.entity.Heater;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class addHeater extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        JdbcHeaterDao heaterDao = new JdbcHeaterDao();
        Heater heater = new Heater();
        heater.setName(req.getParameter("name"));
        if(heater.getName().length() >= 3) {
            heaterDao.create(heater);
            resp.getWriter().print(heaterDao.getLast().toString());
        } else {
            resp.getWriter().print("{\"error\":\"Name.length < 3!\"}");
        }
    }
}
