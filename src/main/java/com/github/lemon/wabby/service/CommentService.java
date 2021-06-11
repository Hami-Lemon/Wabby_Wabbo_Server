package com.github.lemon.wabby.service;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.lemon.wabby.dao.ICommentsDao;
import com.github.lemon.wabby.enums.StatusCode;
import com.github.lemon.wabby.pojo.CommentsPo;
import com.github.lemon.wabby.pojo.dto.BaseDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yui
 */
@Service
public class CommentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
    private final ICommentsDao dao;
    private final AipContentCensor censor;
    private final Cache<Integer, Integer> cache;

    @Autowired
    public CommentService(ICommentsDao dao,
                          AipContentCensor censor,
                          @Qualifier("commentsCache") Cache<Integer, Integer> cache) {
        this.dao = dao;
        this.censor = censor;
        this.cache = cache;
    }


    public BaseDto<Void> postComment(CommentsPo comments) {
        BaseDto<Void> dto = new BaseDto<>();
        try {
            final JSONObject response =
                    censor.textCensorUserDefined(comments.getContent());
            if (response.getInt("conclusionType") == 1) {
                dao.releaseCom(comments);
                dto.setCode(StatusCode.OK);
                dto.setMsg("OK");
            } else {
                StringBuilder msg = new StringBuilder("存在");
                JSONArray data = response.getJSONArray("data");

                for (int i = 0; i < data.length(); i++) {
                    JSONObject d = data.getJSONObject(i);
                    if (d.getInt("type") == 12) {
                        switch (d.getInt("subType")) {
                            case 0:
                                msg.append("-低质灌水-");
                                break;
                            case 1:
                                msg.append("-暴恐违禁-");
                                break;
                            case 2:
                                msg.append("-文本色情-");
                                break;
                            case 3:
                                msg.append("-政治敏感-");
                                break;
                            case 4:
                                msg.append("-恶意推广-");
                                break;
                            case 5:
                                msg.append("-低俗辱骂-");
                                break;
                        }
                    }
                }
                msg.append("不合规内容，请修改后重试");
                dto.setCode(StatusCode.SERVER_ERROR);
                dto.setMsg(msg.toString());
            }
        } catch (DataAccessException e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常，发布失败！");
        } catch (JSONException e) {
            dto.setCode(StatusCode.SERVER_ERROR);
            dto.setMsg("发布失败");
        }
        return dto;
    }

    public BaseDto<List<CommentsPo>> getComment(int tid, int page) {
        BaseDto<List<CommentsPo>> dto = new BaseDto<>();
        try {
            final List<CommentsPo> comments = dao.getCommentsByTipsId(tid, page);
            comments.forEach(this::judgeStarNum);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            dto.setData(comments);
            if (comments.isEmpty()) {
                dto.setMsg("获取到空数据");
            }
        } catch (DataAccessException e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常，发布失败！");
        }
        return dto;
    }

    public BaseDto<List<CommentsPo>> getHotCom(int tid) {
        BaseDto<List<CommentsPo>> dto = new BaseDto<>();
        try {
            final List<CommentsPo> hotComments = dao.getHotComments(tid);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            dto.setData(hotComments);
            if (hotComments.isEmpty()) {
                dto.setMsg("获取到空数据");
            }
        } catch (DataAccessException e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常，发布失败！");
        }
        return dto;
    }

    public BaseDto<Void> addHotNum(int id, int addNum) {
        BaseDto<Void> dto = new BaseDto<>();
        Integer additionHotNum = cache.getIfPresent(id);
        int additional = additionHotNum == null ? 0 : additionHotNum;
        cache.put(id, additional + addNum);
        dto.setCode(StatusCode.OK);
        dto.setMsg("OK");
        return dto;
    }

    private void judgeStarNum(CommentsPo commentsPo) {
        Integer additionStarNum = cache.getIfPresent(commentsPo.getId());
        int additional = additionStarNum == null ? 0 : additionStarNum;
        int oldStarNum = commentsPo.getStarNum();
        commentsPo.setStarNum(oldStarNum + additional);
    }
}
