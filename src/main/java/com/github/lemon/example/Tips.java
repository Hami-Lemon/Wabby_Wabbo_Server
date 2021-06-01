package com.github.lemon.example;

public class Tips {
    private int id;
    private String date;
    private String type;
    private int starNum;
    private String content;

    public Tips(int id, String date, String type, int starNum, String content) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.starNum = starNum;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
