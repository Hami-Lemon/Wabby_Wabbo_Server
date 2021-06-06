package com.github.lemon.wabby.service;

import com.github.lemon.wabby.dao.ICommentDao;
import com.github.lemon.wabby.pojo.CommentsEntity;
import com.github.lemon.wabby.pojo.CommentsResp;
import com.github.lemon.wabby.pojo.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yui
 */
@Service
public class CommentService {

    @Autowired
    private ICommentDao dao;

    public Resp postComment(CommentsEntity comment){
        Resp resp = new Resp();
        try {
            dao.releaseCom(comment);
            resp.setCode(200);
            resp.setMsg("成功");
        } catch (Exception e) {
            resp.setCode(500);
            resp.setMsg("发布失败");
        }
        return resp;
    }

    public CommentsResp getComment(int tid,int page){
        CommentsResp resp = new CommentsResp();
        try {
            List<CommentsEntity> comments = dao.getCommentsByTipsId(tid, page);
            resp.setCode(200);
            resp.setMsg("成功");
            resp.setData(comments);
        } catch (Exception e) {
            resp.setCode(500);
            resp.setMsg("失败");
        }
        return resp;
    }

    public CommentsResp getHotCom(int tid){
        CommentsResp resp = new CommentsResp();
        try {
            List<CommentsEntity> comments = dao.getHotComments(tid);
            resp.setCode(200);
            resp.setMsg("成功");
            resp.setData(comments);
        } catch (Exception e) {
            resp.setCode(500);
            resp.setMsg("失败");
        }
        return resp;
    }
}
