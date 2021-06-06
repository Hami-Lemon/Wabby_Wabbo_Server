package com.github.lemon.wabby.controller;

import com.github.lemon.wabby.pojo.CommentsEntity;
import com.github.lemon.wabby.pojo.CommentsResp;
import com.github.lemon.wabby.pojo.Resp;
import com.github.lemon.wabby.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 成功时code为200，失败时为500
 * @author Yui
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService service;

    /**
     * 发布评论
     * @param comment 评论的json
     * @return code和msg的json
     */
    @RequestMapping(value = "/postcomment",method = RequestMethod.POST)
    public @ResponseBody Resp postComment(@RequestBody CommentsEntity comment){
        Resp resp = service.postComment(comment);
        return resp;
    }

    /**
     * 获取评论
     * @param tid 所属帖子的id
     * @param page 页数
     * @return
     */
    @RequestMapping(value = "/getcomment",method = RequestMethod.GET)
    public @ResponseBody CommentsResp getComment(@RequestParam("tid") int tid,@RequestParam("page") int page){
        CommentsResp resp = service.getComment(tid, page);
        return resp;
    }

    /**
     * 获取热评
     * @param tid 是帖子的id
     * @return
     */
    @RequestMapping(value = "/gethotcom",method = RequestMethod.GET)
    public @ResponseBody CommentsResp getHotCom(@RequestParam("tid") int tid){
        CommentsResp resp = service.getHotCom(tid);
        return resp;
    }
}
