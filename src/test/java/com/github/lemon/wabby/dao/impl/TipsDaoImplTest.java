package com.github.lemon.wabby.dao.impl;

import com.github.lemon.wabby.dao.ITipsDao;
import com.github.lemon.wabby.pojo.TipsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


/**
 * <p> 创建时间 2021/6/5 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring_config.xml")
public class TipsDaoImplTest {

    @Autowired
    ITipsDao tipsDao;

    @Test
    public void releaseTips() {
        TipsEntity tips = new TipsEntity(0, LocalDateTime.now(), "学习", 100, "测试测试测试");
        for (int i = 0; i < 12; i++) {
            tipsDao.releaseTips(tips);
        }
    }

    @Test
    public void getTipsByType() {
        final List<TipsEntity> page1 = tipsDao.getTipsByType("学习", 1);
        System.out.println("page1:");
        page1.forEach(System.out::println);
        final List<TipsEntity> page2 = tipsDao.getTipsByType("学习", 2);
        System.out.println("page2:");
        page2.forEach(System.out::println);
    }

    @Test
    public void getPageNum() {
        final int pageNum = tipsDao.getPageNum("学习");
        assertThat(pageNum).as("学习帖子的总页数")
                .isEqualTo(2);
    }

    @Test
    public void getTipsById() {
        final TipsEntity tips = tipsDao.getTipsById(32);
        System.out.println(tips);
    }

    @Test
    public void getHotTips() {
        final List<TipsEntity> hotTips = tipsDao.getHotTips();
        hotTips.forEach(System.out::println);
    }
}