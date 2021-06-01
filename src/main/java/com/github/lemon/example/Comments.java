package com.github.lemon.example;

public class Comments {
    private int id;
    private String content;
    private int starNum;
    private String date;
    private int floor;
    private int Tips_id;

    public Comments(int id, String content, int starNum, String date, int floor, int tips_id) {
        this.id = id;
        this.content = content;
        this.starNum = starNum;
        this.date = date;
        this.floor = floor;
        Tips_id = tips_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getTips_id() {
        return Tips_id;
    }

    public void setTips_id(int tips_id) {
        Tips_id = tips_id;
    }
}
