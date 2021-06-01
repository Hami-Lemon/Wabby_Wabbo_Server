package com.github.lemon.pojo;

/**
 * 评论实体
 * @author BeCai
 */
public class CommentsEntity {
    /**
     * 评论id,主键，自增长
     */
    private int id;
    /**
     * 评论的具体内容，纯文本
     */
    private String content;
    /**
     * 点赞数
     */
    private int starNum;
    /**
     * 评论发布日期yyyy-MM-dd HH:mm:ss
     */
    private String date;
    /**
     * 评论的楼层数，根据发布时间排序
     */
    private int floor;
    /**
     * 该评论所属的帖子的id
     */
    private int Tips_id;

    public CommentsEntity(int id, String content, int starNum, String date, int floor, int tips_id) {
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
