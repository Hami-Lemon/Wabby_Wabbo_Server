package com.github.lemon.wabby.controller;

import com.github.lemon.wabby.pojo.Resp;
import com.github.lemon.wabby.pojo.TipsEntity;
import com.github.lemon.wabby.pojo.TipsResp;
import com.github.lemon.wabby.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 成功时code为200，失败时为500
 *
 * @author Yui
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class TipsController {

    private final TipsService service;

    @Autowired
    public TipsController(TipsService service) {
        this.service = service;
    }

    /**
     * 发布帖子
     *
     * @param tip
     * @return 带有code和msg的json
     */
    @RequestMapping(value = "/posttips", method = RequestMethod.POST)
    public @ResponseBody
    Resp postTips(@RequestBody TipsEntity tip) {
        Resp resp = service.postTips(tip);
        return resp;
    }

    /**
     * 获取帖子
     *
     * @param type 帖子类型
     * @param page 页数
     * @return 带有code、msg和Tips对象集合的json
     */
    @RequestMapping(value = "/gettips", method = RequestMethod.GET)
    public @ResponseBody
    TipsResp getTips(@RequestParam("type") String type, @RequestParam("page") int page) {
        TipsResp resp = service.getTips(type, page);
        return resp;
    }

    /**
     * 根据id获取帖子
     *
     * @param id 帖子id
     * @return 带有code、msg和只有一个Tips对象或null集合的json
     */
    @RequestMapping(value = "/getdetail", method = RequestMethod.GET)
    public @ResponseBody
    TipsResp getTipsById(@RequestParam("id") int id) {
        TipsResp resp = service.getTipsById(id);
        return resp;
    }

    /**
     * 获取热帖
     *
     * @return
     */
    @RequestMapping(value = "/gethottips", method = RequestMethod.GET)
    public @ResponseBody
    TipsResp getHotTips() {
        TipsResp resp = service.getHotTips();
        return resp;
    }
}
