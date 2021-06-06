package com.github.lemon.wabby.pojo;

import java.util.List;

/**
 * @author Yui
 */
public class CommentsResp {
    private int code;
    private String msg;
    private List<CommentsEntity> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CommentsEntity> getData() {
        return data;
    }

    public void setData(List<CommentsEntity> data) {
        this.data = data;
    }
}
