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
    protected SearchHeater generateQuery(HttpServletRequest req) {
        SearchHeater temp = new SearchHeater();

        if(!req.getParameter("offset").equals("null")) {
            temp.setOffset(req.getParameter("offset"));
        } else {
            temp.setOffset(null);
        }

        if(!req.getParameter("amount").equals("null")) {
            temp.setAmount(req.getParameter("amount"));
        } else {
            temp.setAmount(null);
        }

        if(!req.getParameter("type").equals("null")) {
            temp.setType(req.getParameter("type"));
        } else {
            temp.setType(null);
        }

        if(!req.getParameter("producer").equals("null")) {
            temp.setProducer(req.getParameter("producer"));
        } else {
            temp.setProducer(null);
        }

        if(!req.getParameter("coveringFrom").equals("null")) {
            temp.setMinCovering(req.getParameter("coveringFrom"));
        } else {
            temp.setMinCovering(null);
        }

        if(!req.getParameter("coveringTo").equals("null")) {
            temp.setMaxCovering(req.getParameter("coveringTo"));
        } else {
            temp.setMaxCovering(null);
        }

        if(!req.getParameter("powerFrom").equals("null")) {
            temp.setMinPower(req.getParameter("powerFrom"));
        } else {
            temp.setMinPower(null);
        }

        if(!req.getParameter("powerTo").equals("null")) {
            temp.setMaxPower(req.getParameter("powerTo"));
        } else {
            temp.setMaxPower(null);
        }

        if(!req.getParameter("protection").equals("null")) {
            temp.setProtection(req.getParameter("protection"));
        } else {
            temp.setProtection(null);
        }

        if(!req.getParameter("priceFrom").equals("null")) {
            temp.setMinPrice(req.getParameter("priceFrom"));
        } else {
            temp.setMinPrice(null);
        }

        if(!req.getParameter("priceTo").equals("null")) {
            temp.setMaxPrice(req.getParameter("priceTo"));
        } else {
            temp.setMaxPrice(null);
        }

        return temp;
    }

    protected String generateJSON(Heater[] target) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        if(target.length > 0) {
            buffer.append(target[0].toString());
            for(int i = 0; i<target.length; i++) {
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
