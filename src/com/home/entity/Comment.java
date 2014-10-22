package com.home.entity;

import java.sql.Date;

public class Comment {
    String id;
    String name;
    String text;
    Date data;
    String heater_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHeater_id() {
        return heater_id;
    }

    public void setHeater_id(String heater_id) {
        this.heater_id = heater_id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"comment\":\"" + text + '\"' +
                ", \"date\":\"" + data + '\"' +
                '}';
    }
}
