package com.github.lemon.dao.impl;

import com.github.lemon.dao.ICommentDao;
import com.github.lemon.pojo.CommentsEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author BeCai
 * @version v1.0
 */
@Repository
public class CommentDaoImpl implements ICommentDao {
    /**
     * @see QueryRunner
     */
    private final QueryRunner runner;
    private final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CommentDaoImpl(@Autowired DataSource dataSource) {
        //获取数据库操作对象
        runner = new QueryRunner(dataSource);
    }

    //插入数据（发评论）
    public void releaseCom(CommentsEntity commentsEntity) throws SQLException {
        runner.update("insert into comment(content,starNum,date,floor,Tips_id) values (?,?,?,?,?)",
                commentsEntity.getContent(), commentsEntity.getStarNum(), commentsEntity.getDate(), commentsEntity.getFloor(), commentsEntity.getTips_id());
    }

    //根据id获取评论
    public List<CommentsEntity> getCommentsById(int TipId) throws ParseException, SQLException {
        List<CommentsEntity> ComList = new ArrayList<>();
        List<Object[]> list = runner.query("select * from comment where Tips_id = ?", new ArrayListHandler(), TipId);
        for (Object[] objects : list) {
            Date date = fmt.parse(String.valueOf(objects[3]));
            CommentsEntity commentsEntity = new CommentsEntity((int) objects[0], (String) objects[1], (int) objects[2], fmt.format(date), (int) objects[4], (int) objects[5]);
            ComList.add(commentsEntity);
        }
        return ComList;
    }

    //获取当前帖子的热评（点赞数前3）
    public List<CommentsEntity> getTipsOrderByStar(int TipId) throws SQLException, ParseException {
        List<CommentsEntity> ComOrderList = new ArrayList<>();
        List<Object[]> list = runner.query("select * from comment where Tips_id = ? order by starNum DESC limit 3", new ArrayListHandler(), TipId);
        for (Object[] objects : list) {
            Date date = fmt.parse(String.valueOf(objects[3]));
            CommentsEntity commentsEntity = new CommentsEntity((int) objects[0], (String) objects[1], (int) objects[2], fmt.format(date), (int) objects[4], (int) objects[5]);
            ComOrderList.add(commentsEntity);
        }
        return ComOrderList;
    }
}
