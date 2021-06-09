package com.github.lemon.wabby.service;

import com.github.lemon.wabby.dao.ITipsDao;
import com.github.lemon.wabby.enums.StatusCode;
import com.github.lemon.wabby.pojo.TipsPo;
import com.github.lemon.wabby.pojo.dto.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yui
 */
@Service
public class TipsService {

    private final ITipsDao dao;

    @Autowired
    public TipsService(ITipsDao dao) {
        this.dao = dao;
    }

    public BaseDto<Void> postTips(TipsPo tip) {
        BaseDto<Void> dto = new BaseDto<>();
        try {
            dao.releaseTips(tip);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
        } catch (DataAccessException e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常，发布失败！");
        }
        return dto;
    }

    public BaseDto<List<TipsPo>> getTips(String type, int page) {
        BaseDto<List<TipsPo>> dto = new BaseDto<>();
        List<TipsPo> tips;
        try {
            tips = dao.getTipsByType(type, page);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            dto.setData(tips);
            if (tips.isEmpty()) {
                dto.setMsg("该分类下暂时没有相关的帖子信息");
            }
        } catch (DataAccessException e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常, 无法获取到相关数据");
        }

        return dto;
    }

    public BaseDto<TipsPo> getTipsById(int id) {
        BaseDto<TipsPo> dto = new BaseDto<>();
        try {
            final TipsPo tips = dao.getTipsById(id);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            if (tips == null) {
                dto.setMsg("未能获取到相关信息，该帖子可能已被删除");
            }
            dto.setData(tips);
        } catch (DataAccessException e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常");
        }
        return dto;
    }

    public BaseDto<List<TipsPo>> getHotTips() {
        BaseDto<List<TipsPo>> dto = new BaseDto<>();
        try {
            List<TipsPo> tips = dao.getHotTips();
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            dto.setData(tips);
            if (tips.isEmpty()) {
                dto.setMsg("未获取热贴");
            }
        } catch (Exception e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("服务端异常，获取失败");
        }
        return dto;
    }
}
