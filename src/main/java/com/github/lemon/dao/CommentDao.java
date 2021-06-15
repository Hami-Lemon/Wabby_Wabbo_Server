package com.github.lemon.dao;

import com.github.lemon.example.Comments;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDao {
    private QueryRunner runner;
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CommentDao(@Autowired DataSource dataSource) {
        //获取数据库操作对象
        runner = new QueryRunner(dataSource);
    }

    //插入数据（发评论）
    public void releaseCom(Comments comments) throws SQLException {
        runner.update("insert into comment(content,starNum,date,floor,Tips_id) values (?,?,?,?,?)",
                comments.getContent(), comments.getStarNum(), comments.getDate(), comments.getFloor(), comments.getTips_id());
    }
    //根据id获取评论
    public List<Comments> getCommentsById(int TipId) throws ParseException, SQLException {
        List<Comments> ComList = new ArrayList<>();
        List<Object[]> list = runner.query("select * from comment where Tips_id = ?", new ArrayListHandler(), TipId);
        for (Object[] objects:list) {
            Date date = fmt.parse(String.valueOf(objects[3]));
            Comments comments = new Comments((int)objects[0], (String)objects[1], (int)objects[2], fmt.format(date), (int)objects[4], (int)objects[5]);
            ComList.add(comments);
        }
        return ComList;
    }
    //获取当前帖子的热评（点赞数前3）
    public List<Comments> getTipsOrderByStar(int TipId) throws SQLException, ParseException {
        List<Comments> ComOrderList = new ArrayList<>();
        List<Object[]> list = runner.query("select * from comment where Tips_id = ? order by starNum DESC limit 3", new ArrayListHandler(), TipId);
        for (Object[] objects:list) {
            Date date = fmt.parse(String.valueOf(objects[3]));
            Comments comments = new Comments((int)objects[0], (String)objects[1], (int)objects[2], fmt.format(date), (int)objects[4], (int)objects[5]);
            ComOrderList.add(comments);
        }
        return ComOrderList;
    }
}
