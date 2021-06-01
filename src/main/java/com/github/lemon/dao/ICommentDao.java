package com.github.lemon.dao;

import com.github.lemon.pojo.CommentsEntity;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * <p> 创建时间 2021/6/1 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
public interface ICommentDao {
    /**
     * 添加评论
     *
     * @param commentsEntity 评论对象
     * @throws SQLException 查询失败时抛出
     */
    void releaseCom(CommentsEntity commentsEntity) throws SQLException;

    /**
     * 获取对应帖子下的评论
     *
     * @param TipId 帖子的id
     * @return 对应的评论集合，可能为空集合
     * @throws SQLException   查询失败时抛出
     * @throws ParseException 解析日期数据出错时抛出
     */
    List<CommentsEntity> getCommentsById(int TipId) throws SQLException, ParseException;

    /**
     * 获取对应帖子下的热评，以点赞排序，取前3
     *
     * @param TipId 帖子的id
     * @return 热评集合，可能为空集合
     * @throws SQLException   查询失败时抛出
     * @throws ParseException 解析日期数据出错时抛出
     */
    List<CommentsEntity> getTipsOrderByStar(int TipId) throws SQLException, ParseException;


}
