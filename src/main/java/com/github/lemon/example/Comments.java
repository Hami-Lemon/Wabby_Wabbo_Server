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
}
