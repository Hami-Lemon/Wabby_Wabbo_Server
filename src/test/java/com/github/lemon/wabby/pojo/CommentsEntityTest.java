package com.github.lemon.wabby.pojo;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * <p> 创建时间 2021/6/5 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
public class CommentsEntityTest {

    @Test
    public void toJson() {
        CommentsEntity comments = new CommentsEntity(1, "测试评论", 20, LocalDateTime.now(), 2);
        System.out.println(comments.toJson());
    }
}