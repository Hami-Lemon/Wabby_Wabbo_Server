package com.github.lemon.wabby.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.lemon.wabby.util.TimeFormatUtil;

import java.time.LocalDateTime;

/**
 * 帖子实体
 *
 * @author BeCai
 */
public class TipsEntity {
    /**
     * 帖子的id,主键，自增长
     */
    private int id;
    /**
     * 发帖时间 yyyy-MM-dd HH:mm:ss
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
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

    public TipsEntity() {
    }

    public TipsEntity(int id, LocalDateTime date, String type, int starNum, String content) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    @Override
    public String toString() {
        return "TipsEntity{" +
                "id=" + id +
                ", date=" + TimeFormatUtil.format(date) +
                ", type='" + type + '\'' +
                ", starNum=" + starNum +
                ", content='" + content + '\'' +
                '}';
    }

    public String toJson() {
        return "{\n" +
                "    \"id\":\"" + id + "\",\n" +
                "    \"date\":\"" + TimeFormatUtil.format(date) + "\",\n" +
                "    \"type\":\"" + type + "\",\n" +
                "    \"starNum\":" + starNum + ",\n" +
                "    \"content\":\"" + content + "\"\n" +
                "}";
    }
}
