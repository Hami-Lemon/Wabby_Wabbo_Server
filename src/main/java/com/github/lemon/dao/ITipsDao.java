package com.github.lemon.dao;

import com.github.lemon.pojo.TipsEntity;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * <p> 创建时间 2021/6/1 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
public interface ITipsDao {
    /**
     * 保存贴子
     *
     * @param tip tip对象
     * @throws SQLException 保存失败时抛出异常
     */
    void releaseTips(TipsEntity tip) throws SQLException;

    /**
     * 根据type查询对应的帖子，结果可能为空集合
     *
     * @param type 帖子类型
     * @return 查询到的帖子集合，可能为空集合
     * @throws SQLException   查询失败时抛出
     * @throws ParseException 解析日期数据出错时抛出
     */
    List<TipsEntity> getTipsByType(String type) throws SQLException, ParseException;

    /**
     * 根据帖子id获取到对应的帖子
     *
     * @param id id
     * @return 查询到的帖子，可能为null
     * @throws SQLException   查询失败时抛出
     * @throws ParseException 解析日期数据出错时抛出
     */
    TipsEntity getTipById(int id) throws SQLException, ParseException;

    /**
     * 根据点赞数获取前10的帖子（热贴）
     * 可能为空集合
     * 可能集合数小于10
     *
     * @return 热贴集合
     * @throws SQLException   查询失败时抛出
     * @throws ParseException 解析日期数据出错时抛出
     */
    List<TipsEntity> getTipsOrderByStar() throws SQLException, ParseException;

}
