package com.github.lemon.wabby.dao.impl;

import com.github.lemon.wabby.dao.ICommentDao;
import com.github.lemon.wabby.pojo.CommentsPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    private final static Logger LOGGER = LoggerFactory.getLogger(CommentsDaoImpl.class);
    /**
     * 分页查询中，每页的容量
     */
    private final int pageSize = 10;
//    private final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CommentsDaoImpl(@Autowired JdbcTemplate template) {
        LOGGER.debug("template is null? {}", template == null);
        //获取数据库操作对象
        this.template = template;
    }

    //插入数据（发评论）
    public void releaseCom(CommentsPo commentsPo) {
        String sql = "insert into Comments(content, starNum, date, tips_id) value (?,?,?,?) ";
        LOGGER.info("release comments:{} args:{}", sql, commentsPo);
        int result = template.update(sql,
                commentsPo.getContent(),
                commentsPo.getStarNum(),
                commentsPo.getDate(),
                commentsPo.getTipsId());
        LOGGER.info("result:{}",result);
    }

    @Override
    public List<CommentsPo> getCommentsByTipsId(int tipId, int page) {
        //调用定义好的存储过程
        //language=MySQL
        String sql = "call query_comments_by_type_with_page(?, ?, ? )";
        return getCommentsList(sql, tipId, page, pageSize);
    }

    @Override
    public int getPageNum(int tipsId) {
        String sql = "select count(*) from Comments where tips_id = ? ";
        Integer commentsNum = template.queryForObject(sql, Integer.class, tipsId);
        LOGGER.info("query commentsNum with {} args:{} num:{} pageSize:{}",
                sql, tipsId, commentsNum, pageSize);
        if (commentsNum == null || commentsNum == 0) return 0;
        return (commentsNum + pageSize - 1) / pageSize;
    }

    //获取当前帖子的热评（点赞数前3）
    public List<CommentsPo> getHotComments(int tipsId) {
        String sql = "select * from Comments where tips_id = ? order by starNum desc limit 3 ";
        return getCommentsList(sql, tipsId);
    }

    private List<CommentsPo> getCommentsList(String sql, Object... args) {
        List<CommentsPo> list = template.query(sql,
                new BeanPropertyRowMapper<>(CommentsPo.class),
                args);
        LOGGER.info("query commentsList {} args:{} result:{}", sql, args, list);
        return list;
    }
}
