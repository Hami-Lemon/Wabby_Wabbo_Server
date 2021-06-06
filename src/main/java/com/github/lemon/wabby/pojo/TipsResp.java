package com.github.lemon.wabby.pojo;

import java.util.List;

/**
 * @author Yui
 */
public class TipsResp {
    private int code;
    private String msg;
    private List<TipsEntity> data;

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

    public List<TipsEntity> getData() {
        return data;
    }

    public void setData(List<TipsEntity> data) {
        this.data = data;
    }

}
