package com.home.servlet;

import com.home.dao.JdbcHeaterDao;
import com.home.entity.Heater;
import com.home.query.SearchHeater;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class editHeater extends HttpServlet{
    protected boolean valid(String target) {
        if(target != null) {
            if(!target.equals("null")) {
                return true;
            }
        }
        return false;
    }

    private Heater genHeater(HttpServletRequest req, Heater old) {
        Heater temp = old;
        if(valid(req.getParameter("name"))) {
            temp.setName(req.getParameter("name"));
        }

        if(valid(req.getParameter("type"))) {
            temp.setType(req.getParameter("type"));
        }

        if(valid(req.getParameter("producer"))) {
            temp.setProducer(req.getParameter("producer"));
        }

        if(valid(req.getParameter("covering"))) {
            temp.setCovering(req.getParameter("covering"));
        }

        if(valid(req.getParameter("power"))) {
            temp.setPower(req.getParameter("power"));
        }

        if(valid(req.getParameter("protection"))) {
            temp.setProtection(req.getParameter("protection"));
        }

        if(valid(req.getParameter("price"))) {
            temp.setPrice(req.getParameter("price"));
        }
        return temp;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        if(id != null) {
            JdbcHeaterDao heaterDao = new JdbcHeaterDao();
            SearchHeater searchHeater = new SearchHeater();
            searchHeater.setId(req.getParameter("id"));
            Heater[] old = heaterDao.find(searchHeater);
            Heater target = genHeater(req,old[0]);

            heaterDao.update(target);
        } else {
            resp.getWriter().print("ERROR! ID not found!");
        }
    }
}
