package com.github.lemon.wabby.dao.impl;

import com.github.lemon.wabby.dao.ICommentDao;
import com.github.lemon.wabby.pojo.CommentsEntity;
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
public class CommentsDaoImplTest {
    @Autowired
    ICommentDao commentDao;

    @Test
    public void releaseCom() {
        CommentsEntity commentsEntity =
                new CommentsEntity(0, "测试测试评论", 20, LocalDateTime.now(), 32);
        for (int i = 0; i < 12; i++) {
            commentDao.releaseCom(commentsEntity);
        }
    }

    @Test
    public void getCommentsByTipsId() {
        final List<CommentsEntity> page1 = commentDao.getCommentsByTipsId(32, 1);
        System.out.println("page1:");
        page1.forEach(System.out::println);
        final List<CommentsEntity> page2 = commentDao.getCommentsByTipsId(32, 2);
        System.out.println("page2:");
        page2.forEach(System.out::println);

    }

    @Test
    public void getPageNum() {
        final int pageNum = commentDao.getPageNum(32);
        assertThat(pageNum).as("id为32下的评论分页总数")
                .isEqualTo(2);
    }

    @Test
    public void getHotComments() {
        final List<CommentsEntity> hotComments = commentDao.getHotComments(32);
        hotComments.forEach(System.out::println);
    }
}