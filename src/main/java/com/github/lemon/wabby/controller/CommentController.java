package com.github.lemon.wabby.controller;

import com.github.lemon.wabby.pojo.CommentsPo;
import com.github.lemon.wabby.pojo.dto.BaseDto;
import com.github.lemon.wabby.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 成功时code为200，失败时为500
 *
 * @author Yui
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class CommentController {

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    /**
     * 发布评论
     *
     * @param comments 评论的json
     * @return code和msg的json
     */
    @PostMapping(value = "/postcomments",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<Void> postComment(@RequestBody CommentsPo comments) {
        return service.postComment(comments);
    }

    /**
     * 获取评论
     *
     * @param tid  所属帖子的id
     * @param page 页数
     * @return
     */
    @GetMapping(value = "/getcomments",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<List<CommentsPo>>
    getComments(@RequestParam("tid") int tid, @RequestParam("page") int page) {
        return service.getComment(tid, page);
    }

    /**
     * 获取热评
     *
     * @param tid 是帖子的id
     * @return
     */
    @GetMapping(value = "/gethotcomments",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<List<CommentsPo>> getHotCom(@RequestParam("tid") int tid) {
        return service.getHotCom(tid);
    }
}
