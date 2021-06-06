package com.github.lemon.wabby.dao.impl;

import com.github.lemon.wabby.dao.ITipsDao;
import com.github.lemon.wabby.pojo.TipsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * @author BeCai
 * @version v1.0
 */
@Repository
public class TipsDaoImpl implements ITipsDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(TipsDaoImpl.class);
    /**
     * 分页之后,每页的帖子数
     */
    private final int pageSize = 10;
    /**
     * @see JdbcTemplate
     */
    private final JdbcTemplate template;
//    private final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TipsDaoImpl(@Autowired JdbcTemplate template) {
        LOGGER.debug("template is null? {}", template == null);
        //获取数据库操作对象
        this.template = template;
    }

    //插入数据（发帖）
    public void releaseTips(TipsEntity tip) {
        String sql = "insert into Tips(date,type,starNum,content) values(?,?,?,?) ";
        int result = template.update(sql,
                tip.getDate(),
                tip.getType(),
                tip.getStarNum(),
                tip.getContent());
        LOGGER.info("release tips {} args:[{}] affect line:{}", sql, tip, result);
    }

    @Override
    @Deprecated(forRemoval = true)
    public List<TipsEntity> getTisByType(String type) {
        return getTipsByType(type, 1);
    }

    //根据类型获取帖子
    @Override
    public List<TipsEntity> getTipsByType(String type, int page) {
        //调用定义好的存储过程
        String sql = "call query_tips_by_type_with_page(?,?,?)";
        return getTipsList(sql, type, page, pageSize);
    }

    //获取总页数
    @Override
    public int getPageNum(String type) {
        String sql = "select count(*) from Tips where type = ? ";
        Integer tipsNum = template.queryForObject(sql, Integer.class, type);
        LOGGER.info("query pageNum {} type:{} tipsNum:{} pageSize:{}",
                sql, type, tipsNum, pageSize);
        if (tipsNum == null || tipsNum == 0) return 0;
        return (tipsNum + pageSize - 1) / pageSize;
    }

    //根据id获取帖子
    public TipsEntity getTipsById(int id) {
        String sql = "select * from Tips where id = ? ";
        TipsEntity tips = template.queryForObject(sql,
                new BeanPropertyRowMapper<>(TipsEntity.class),
                id);
        LOGGER.info("query tips by id {} id:{} tips:{}", sql, id, tips);
        return tips;
    }

    //点赞数降序排列获取帖子（前10）
    public List<TipsEntity> getHotTips() {
        String sql = "select * from Tips order by starNum desc limit 10";
        return getTipsList(sql);
    }

    private List<TipsEntity> getTipsList(String sql, Object... args) {
        List<TipsEntity> list = template.query(sql,
                new BeanPropertyRowMapper<>(TipsEntity.class),
                args);
        LOGGER.info("query tipsList {}, args:{} result:{}",sql, args, list);
        return list;
    }
}
