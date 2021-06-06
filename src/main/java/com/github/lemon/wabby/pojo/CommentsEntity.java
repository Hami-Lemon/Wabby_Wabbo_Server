package com.github.lemon.wabby.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.lemon.wabby.util.TimeFormatUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 评论实体
 *
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
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    /**
     * 该评论所属的帖子的id
     */
    private int tipsId;

    public CommentsEntity() {
    }

    public CommentsEntity(int id, String content, int starNum, LocalDateTime date, int tipsId) {
        this.id = id;
        this.content = content;
        this.starNum = starNum;
        this.date = date;
        this.tipsId = tipsId;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTipsId() {
        return tipsId;
    }

    public void setTipsId(int tipsId) {
        this.tipsId = tipsId;
    }

    @Override
    public String toString() {
        return "CommentsEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", starNum=" + starNum +
                ", date='" + TimeFormatUtil.format(date) + '\'' +
                ", Tips_id=" + tipsId +
                '}';
    }

    public String toJson() {
        return "{\n" +
                "    \"id\":\"" + id + "\",\n" +
                "    \"content\":\"" + content + "\",\n" +
                "    \"starNum\":" + starNum + ",\n" +
                "    \"date\":\"" + TimeFormatUtil.format(date) + "\",\n" +
                "    \"tipsId\":" + tipsId + "\n" +
                "}";
    }
}
