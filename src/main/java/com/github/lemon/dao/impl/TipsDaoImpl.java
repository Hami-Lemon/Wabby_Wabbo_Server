package com.github.lemon.dao.impl;

import com.github.lemon.dao.ITipsDao;
import com.github.lemon.pojo.TipsEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
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
public class TipsDaoImpl implements ITipsDao {
    private final QueryRunner runner;
    private final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TipsDaoImpl(@Autowired DataSource dataSource) {
        //获取数据库操作对象
        runner = new QueryRunner(dataSource);
    }

    //插入数据（发帖）
    public void releaseTips(TipsEntity tip) throws SQLException {
        runner.update("insert into Tips(date,type,starNum,content) values (?,?,?,?)",
                tip.getDate(), tip.getType(), tip.getStarNum(), tip.getContent());
    }

    //根据类型获取帖子
    public List<TipsEntity> getTipsByType(String type) throws SQLException, ParseException {
        List<TipsEntity> TipList = new ArrayList<>();
        List<Object[]> list = runner.query("select * from Tips where Type = ?", new ArrayListHandler(), type);
        for (Object[] objects : list) {
            Date date = fmt.parse(String.valueOf(objects[1]));
            TipsEntity tip = new TipsEntity((int) objects[0], fmt.format(date), (String) objects[2], (int) objects[3], (String) objects[4]);
            TipList.add(tip);
        }
        return TipList;
    }

    //根据id获取帖子
    public TipsEntity getTipById(int id) throws SQLException, ParseException {
        Object[] objects = runner.query("select * from Tips where id = ?", new ArrayHandler(), id);
        Date date = fmt.parse(String.valueOf(objects[1]));
        return new TipsEntity((int) objects[0], fmt.format(date), (String) objects[2], (int) objects[3], (String) objects[4]);
    }

    //点赞数降序排列获取帖子（前10）
    public List<TipsEntity> getTipsOrderByStar() throws SQLException, ParseException {
        List<TipsEntity> TipOrderList = new ArrayList<>();
        List<Object[]> list = runner.query("select * from Tips order by starNum DESC limit 10", new ArrayListHandler());
        for (Object[] objects : list) {
            Date date = fmt.parse(String.valueOf(objects[1]));
            TipsEntity tip = new TipsEntity((int) objects[0], fmt.format(date), (String) objects[2], (int) objects[3], (String) objects[4]);
            TipOrderList.add(tip);
        }
        return TipOrderList;
    }
}
