package com.github.lemon.wabby.service;

import com.github.lemon.wabby.dao.ICommentDao;
import com.github.lemon.wabby.enums.StatusCode;
import com.github.lemon.wabby.pojo.CommentsPo;
import com.github.lemon.wabby.pojo.dto.BaseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yui
 */
@Service
public class CommentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
    private final ICommentDao dao;

    @Autowired
    public CommentService(ICommentDao dao) {
        this.dao = dao;
    }


    public BaseDto<Void> postComment(CommentsPo comments) {
        BaseDto<Void> dto = new BaseDto<>();
        try{
            dao.releaseCom(comments);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
        }catch (DataAccessException e){
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常，发布失败！");
        }
        return dto;
    }

    public BaseDto<List<CommentsPo>> getComment(int tid, int page) {
        BaseDto<List<CommentsPo>> dto = new BaseDto<>();
        try{
            final List<CommentsPo> comments = dao.getCommentsByTipsId(tid, page);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            dto.setData(comments);
            if(comments.isEmpty()){
                dto.setMsg("获取到空数据");
            }
        }catch (DataAccessException e){
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常，发布失败！");
        }
        return dto;
    }

    public BaseDto<List<CommentsPo>> getHotCom(int tid) {
        BaseDto<List<CommentsPo>> dto = new BaseDto<>();
        try{
            final List<CommentsPo> hotComments = dao.getHotComments(tid);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            dto.setData(hotComments);
            if(hotComments.isEmpty()){
                dto.setMsg("获取到空数据");
            }
        }catch (DataAccessException e){
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常，发布失败！");
        }
        return dto;
    }
}
