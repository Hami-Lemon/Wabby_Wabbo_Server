package com.github.lemon.wabby.dao.impl;

import com.github.lemon.wabby.dao.ICommentDao;
import com.github.lemon.wabby.pojo.CommentsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author BeCai
 * @version v1.0
 */
@Repository
public class CommentsDaoImpl implements ICommentDao {
    /**
     * jdbcTemplate
     *
     * @see JdbcTemplate
     */
    private final JdbcTemplate template;
    /**
     * 分页查询中，每页的容量
     */
    private final int pageSize = 10;
//    private final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CommentsDaoImpl(@Autowired JdbcTemplate template) {
        //获取数据库操作对象
        this.template = template;
    }

    //插入数据（发评论）
    public void releaseCom(CommentsEntity commentsEntity) {
        String sql = "insert into Comments(content, starNum, date, tips_id) value (?,?,?,?) ";
        template.update(sql,
                commentsEntity.getContent(),
                commentsEntity.getStarNum(),
                commentsEntity.getDate(),
                commentsEntity.getTipsId());
    }

    //根据id获取评论
    public List<CommentsEntity> getCommentsByTipsId(int TipId) {
        return getCommentsByTipsId(TipId, 1);
    }

    @Override
    public List<CommentsEntity> getCommentsByTipsId(int tipId, int page) {
        //调用定义好的存储过程
        //language=MySQL
        String sql = "call query_comments_by_type_with_page(?, ?, ? )";
        return getCommentsList(sql, tipId, page, pageSize);
    }

    @Override
    public int getPageNum(int tipsId) {
        String sql = "select count(*) from Comments where tips_id = ? ";
        Integer commentsNum = template.queryForObject(sql, Integer.class, tipsId);
        if (commentsNum == null || commentsNum == 0) return 0;
        return (commentsNum + pageSize - 1) / pageSize;
    }

    //获取当前帖子的热评（点赞数前3）
    public List<CommentsEntity> getHotComments(int tipsId) {
        String sql = "select * from Comments where tips_id = ? order by starNum desc limit 3 ";
        return getCommentsList(sql, tipsId);
    }

    private List<CommentsEntity> getCommentsList(String sql, Object... args) {
        return template.query(sql,
                new BeanPropertyRowMapper<>(CommentsEntity.class),
                args);
    }
}
