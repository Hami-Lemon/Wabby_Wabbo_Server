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
public class TipsEntityTest {

    @Test
    public void toJson() {
        TipsEntity tips = new TipsEntity(1, LocalDateTime.now(), "学习知识", 20, "dsfadfadf测试");
        System.out.println(tips.toJson());
    }
}