package com.home.entity;

import java.util.Arrays;

public class Heater {
    private String id;
    private String name; // Имя
    private String type; // Тип
    private String producer; // Производитель
    private String covering; // Размер помещения
    private String power; // Мощьность
    private String protection; // Функции защиты
    private String price; // Цена

    private Images[] imageses;

    public Heater() {

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCovering() {
        return covering;
    }

    public void setCovering(String covering) {
        this.covering = covering;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Images[] getImageses() {
        return imageses;
    }

    public void setImageses(Images[] imageses) {
        this.imageses = imageses;
    }

    private String getImagesJson() {
        StringBuffer s = new StringBuffer();

        if(imageses != null && imageses.length > 0) {
            s.append(imageses[0]);
            for(int i = 1; i<imageses.length; i++) {
                s.append(", ");
                s.append(imageses[i]);
            }
            return s.toString();
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"type\":\"" + type + '\"' +
                ", \"producer\":\"" + producer + '\"' +
                ", \"covering\":\"" + covering + '\"' +
                ", \"power\":\"" + power + '\"' +
                ", \"protection\":\"" + protection + '\"' +
                ", \"price\":\"" + price + '\"' +
                ", \"images\": [" + getImagesJson() + "]" +
                '}';
    }
}
