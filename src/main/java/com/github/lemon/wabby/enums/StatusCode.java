package com.github.lemon.wabby.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p> 创建时间 2021/6/9 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
public enum StatusCode {
    /**
     * 处理成功
     */
    OK(200),
    /**
     * 服务端处理失败
     */
    SERVER_ERROR(500),
    /**
     * 数据库处理失败
     */
    DB_ERROR(600);
    private final Integer code;

    StatusCode(Integer code) {
        this.code = code;
    }

    @JsonValue
    public Integer getCode() {
        return code;
    }
}
