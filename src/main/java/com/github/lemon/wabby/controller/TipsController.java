package com.github.lemon.wabby.controller;

import com.github.lemon.wabby.pojo.TipsPo;
import com.github.lemon.wabby.pojo.dto.BaseDto;
import com.github.lemon.wabby.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 成功时code为200，失败时为500
 *
 * @author Yui
 */
@RestController
public class TipsController {

    private final TipsService service;

    @Autowired
    public TipsController(TipsService service) {
        this.service = service;
    }

    /**
     * 发布帖子
     *
     * @param tip 客户端传来的帖子数据
     * @return 带有code和msg的json
     */
    @PostMapping(value = "/posttips",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<Void> postTips(@RequestBody TipsPo tip) {
        final BaseDto<Void> dto = service.postTips(tip);
        return dto;
    }

    /**
     * 获取帖子
     *
     * @param type 帖子类型
     * @param page 页数
     * @return 带有code、msg和Tips对象集合的json
     */
    @GetMapping(value = "/gettips",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<List<TipsPo>>
    getTips(@RequestParam("type") String type, @RequestParam("page") int page) {
        return service.getTips(type, page);
    }

    /**
     * 根据id获取帖子
     *
     * @param id 帖子id
     * @return 带有code、msg和只有一个Tips对象或null集合的json
     */
    @GetMapping(value = "/getdetail",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<TipsPo> getTipsById(@RequestParam("id") int id) {
        return service.getTipsById(id);
    }

    /**
     * 获取热帖
     *
     * @return
     */
    @GetMapping(value = "/gethottips",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<List<TipsPo>> getHotTips() {
        return service.getHotTips();
    }

    @GetMapping(value = "/addtipsstarnum",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<Void> addStarNum(@RequestParam("id") int id,
                             @RequestParam(name = "addNum", defaultValue = "1") int addNum) {
        return service.addHotNum(id, addNum);
    }

    @GetMapping(value = "/searchtips",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    BaseDto<List<TipsPo>> searchTips(@RequestParam("content") String content,
                                     @RequestParam(value = "page", defaultValue = "1") int page) {
        return service.searchTips(content, page);
    }

    @GetMapping(value = "/getpagenum",
            produces = "application/json;charset=UTF-8")
    BaseDto<Integer> getPageNum(@RequestParam("type") String type) {
        return service.getPageNum(type);
    }
}
