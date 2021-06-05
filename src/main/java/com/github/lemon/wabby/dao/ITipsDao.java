package com.github.lemon.wabby.dao;

import com.github.lemon.wabby.pojo.TipsEntity;
import org.springframework.dao.DataAccessException;

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
     * @throws DataAccessException 失败时抛出异常
     */
    void releaseTips(TipsEntity tip);

    /**
     * 根据type查询对应的帖子，结果可能为空集合
     * <p>
     * 应使用getTipsByType(String type,int page)方法
     *
     * @param type 帖子类型
     * @return 查询到的帖子集合，可能为空集合
     * @throws DataAccessException 失败时抛出异常
     */
    @Deprecated(forRemoval = true)
    List<TipsEntity> getTisByType(String type);

    /**
     * 根据type查询对应的帖子，结果可能为空集合
     * 由于帖子数量多，为提高传输速度，需要分页
     * 每一页最多返回10个帖子
     *
     * @param type 帖子类型
     * @param page 页码，需要获取第几页的数据
     * @return 查询到的帖子集合，可能为空集合
     * @throws DataAccessException 失败时抛出异常
     */
    List<TipsEntity> getTipsByType(String type, int page);

    /**
     * 获取对应分类下总共有多少页数据，每页最多10条数据
     * 计算公式：总页数 = ( 总帖子数 + 单页个数 - 1 ) / 单页个数
     *
     * @param type 分类
     * @return 总页数
     * @throws DataAccessException 失败时抛出异常
     */
    int getPageNum(String type);

    /**
     * 根据帖子id获取到对应的帖子,id不存在时抛出异常，不会为null
     *
     * @param id id
     * @return 查询到的帖子，不会为null,未查询到时会抛出异常
     * @throws DataAccessException 未查询到对应的id或其它失败情况时抛出异常
     */
    TipsEntity getTipsById(int id);

    /**
     * 根据点赞数获取前10的帖子（热贴）
     * 可能为空集合
     * 可能集合数小于10
     *
     * @return 热贴集合
     * @throws DataAccessException 失败时抛出异常
     */
//    List<TipsEntity> getTipsOrderByStar();
    List<TipsEntity> getHotTips();
}
