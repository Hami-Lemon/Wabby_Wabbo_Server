package com.github.lemon.wabby.dao;

import com.github.lemon.wabby.pojo.CommentsPo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <p> 创建时间 2021/6/1 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
public interface ICommentsDao {
    /**
     * 添加评论
     *
     * @param commentsPo 评论对象
     * @throws DataAccessException 失败时抛出异常
     */
    void releaseCom(CommentsPo commentsPo);

    /**
     * 获取对应帖子下的评论
     * 使用分页查询
     *
     * @param tipId 帖子的id
     * @param page  第几页
     * @return 对应的评论集合，可能为空集合
     * @throws DataAccessException 失败时抛出异常
     */
    List<CommentsPo> getCommentsByTipsId(int tipId, int page);

    /**
     * 获取对应评论下总共有多少页数据，每页最多10条数据
     * 计算公式：总页数 = ( 总帖子数 + 单页个数 - 1 ) / 单页个数
     *
     * @param tipsId 评论所属帖子的id
     * @return 总页数
     * @throws DataAccessException 失败时抛出异常
     */
    int getPageNum(int tipsId);

    /**
     * 获取对应帖子下的热评，以点赞排序，取前3
     *
     * @param tipId 帖子的id
     * @return 热评集合，可能为空集合
     * @throws DataAccessException 失败时抛出异常
     */
    List<CommentsPo> getHotComments(int tipId);

    void addStarNum(int id, int addNum);
}
