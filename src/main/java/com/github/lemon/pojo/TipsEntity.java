package com.github.lemon.pojo;

/**
 * 帖子实体
 * @author BeCai
 */
public class TipsEntity {
    /**
     * 帖子的id,主键，自增长
     */
    private int id;
    /**
     * 发帖时间yyyy-MM-dd HH:mm:ss
     */
    private String date;
    /**
     * 帖子的类型
     */
    private String type;
    /**
     * 点赞数
     */
    private int starNum;
    /**
     * 帖子的具体内容，纯文本
     */
    private String content;

    public TipsEntity(int id, String date, String type, int starNum, String content) {
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
