package com.home.servlet;

import com.home.dao.JdbcHeaterDao;
import com.home.dao.MainJdbcDao;
import com.home.entity.Heater;
import com.home.query.SearchHeater;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class getHeater extends HttpServlet{
    protected boolean valid(String target) {
        if(target != null) {
            if(!target.equals("null")) {
                return true;
            }
        }
        return false;
    }

    protected SearchHeater generateQuery(HttpServletRequest req) {
        SearchHeater temp = new SearchHeater();

        if(valid(req.getParameter("name"))) {
            temp.setName(req.getParameter("name"));
        }

        if(valid(req.getParameter("id"))) {
            temp.setId(req.getParameter("id"));
        }

        if(valid(req.getParameter("offset"))) {
            temp.setOffset(req.getParameter("offset"));
        }

        if(valid(req.getParameter("amount"))) {
            temp.setAmount(req.getParameter("amount"));
        }

        if(valid(req.getParameter("type"))) {
            temp.setType(req.getParameter("type"));
        }

        if(valid(req.getParameter("producer"))) {
            temp.setProducer(req.getParameter("producer"));
        }

        if(valid(req.getParameter("coveringFrom"))) {
            temp.setMinCovering(req.getParameter("coveringFrom"));
        }

        if(valid(req.getParameter("coveringTo"))) {
            temp.setMaxCovering(req.getParameter("coveringTo"));
        }

        if(valid(req.getParameter("powerFrom"))) {
            temp.setMinPower(req.getParameter("powerFrom"));
        }

        if(valid(req.getParameter("powerTo"))) {
            temp.setMaxPower(req.getParameter("powerTo"));
        }

        if(valid(req.getParameter("protection"))) {
            temp.setProtection(req.getParameter("protection"));
        }

        if(valid(req.getParameter("priceFrom"))) {
            temp.setMinPrice(req.getParameter("priceFrom"));
        }

        if(valid(req.getParameter("priceTo"))) {
            temp.setMaxPrice(req.getParameter("priceTo"));
        }

        return temp;
    }

    protected String generateJSON(Heater[] target) {
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
        resp.getWriter().print(generateJSON(new JdbcHeaterDao().find(generateQuery(req))));
    }
}
