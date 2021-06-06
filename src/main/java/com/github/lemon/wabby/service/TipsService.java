package com.github.lemon.wabby.service;

import com.github.lemon.wabby.dao.ITipsDao;
import com.github.lemon.wabby.pojo.Resp;
import com.github.lemon.wabby.pojo.TipsEntity;
import com.github.lemon.wabby.pojo.TipsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yui
 */
@Service
public class TipsService {

    @Autowired
    private ITipsDao dao;

    public Resp postTips(TipsEntity tip){
        Resp resp = new Resp();
        try {
            dao.releaseTips(tip);
            resp.setCode(200);
            resp.setMsg("成功");
        } catch (Exception e) {
            resp.setCode(500);
            resp.setMsg("发布失败");
        }
        return resp;
    }

    public TipsResp getTips(String type,int page){
        TipsResp resp = new TipsResp();
        List<TipsEntity> tips = null;
        try {
            tips = dao.getTipsByType(type, page);
        } catch (Exception e) {
            resp.setCode(500);
            resp.setMsg("获取失败");
        }
        if (tips.size() == 0 || tips == null){
            resp.setCode(500);
            resp.setMsg("获取失败");
        }else {
            resp.setCode(200);
            resp.setMsg("获取成功");
            resp.setData(tips);
        }
        return resp;
    }

    public TipsResp getTipsById(int id){
        TipsResp resp = new TipsResp();
        ArrayList<TipsEntity> list = new ArrayList<>();
        TipsEntity tip = null;
        try {
            tip = dao.getTipsById(id);
            list.add(tip);
            resp.setCode(200);
            resp.setMsg("成功");
            resp.setData(list);
        } catch (Exception e) {
            resp.setCode(500);
            resp.setMsg("失败");
        }
        return resp;
    }

    public TipsResp getHotTips(){
        TipsResp resp = new TipsResp();
        try {
            List<TipsEntity> tips = dao.getHotTips();
            if (tips.size() != 0){
                resp.setCode(200);
                resp.setMsg("成功");
                resp.setData(tips);
            }else {
                resp.setCode(500);
                resp.setMsg("没有帖子");
            }
        } catch (Exception e) {
            resp.setCode(500);
            resp.setMsg("失败");
        }
        return resp;
    }
}
