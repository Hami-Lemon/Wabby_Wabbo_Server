package com.github.lemon.wabby.pojo.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.github.lemon.wabby.enums.StatusCode;

/**
 * 基本数据传输对象
 * <p>
 * 用于与客户端交互
 * <p> 创建时间 2021/6/9 </p>
 *
 * @param <T> 传输数据的类型
 * @author Hami Lemon
 * @version v1.0
 */
public class BaseDto<T> {
    /**
     * 状态码
     */
    private StatusCode code;
    /**
     * 附加信息，主要用于告知错误信息
     */
    private String msg;
    /**
     * 具体传输的数据，可能为null
     */
    private T data;

    public BaseDto() {
    }

    public BaseDto(StatusCode code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
