package com.home.entity;

public class Images {
    private String id;
    private String name;
    private String url;
    private String heater_id;

    public Images() {
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                ", \"url\":\"" + url + '\"' +
                '}';
    }
}
