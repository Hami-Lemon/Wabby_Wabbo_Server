package com.github.lemon.wabby.service;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.github.lemon.wabby.dao.ITipsDao;
import com.github.lemon.wabby.enums.StatusCode;
import com.github.lemon.wabby.pojo.TipsPo;
import com.github.lemon.wabby.pojo.dto.BaseDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    private final AipContentCensor censor;

    @Autowired
    public TipsService(ITipsDao dao,
                       AipContentCensor censor) {
        this.dao = dao;
        this.censor = censor;
    }

    public BaseDto<Void> postTips(TipsPo tip) {
        BaseDto<Void> dto = new BaseDto<>();
        try {
            JSONObject response =
                    censor.textCensorUserDefined(tip.getContent());
            if (response.getInt("conclusionType") == 1) {
                dao.releaseTips(tip);
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
            } else {
                dto.setData(tips);
            }
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

    public BaseDto<Void> addHotNum(int id, int addNum) {
        BaseDto<Void> dto = new BaseDto<>();
        dao.addStarNum(id, addNum);
        dto.setCode(StatusCode.OK);
        dto.setMsg("OK");
        return dto;
    }

    public BaseDto<List<TipsPo>> searchTips(String content, int page) {
        BaseDto<List<TipsPo>> dto = new BaseDto<>();
        try {
            final List<TipsPo> tips = dao.searchTipsByContent(content, page);

            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            dto.setData(tips);
            if (tips.isEmpty()) {
                dto.setMsg("无相关内容");
            }
        } catch (DataAccessException e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("error");
        }
        return dto;
    }

    public BaseDto<Integer> getPageNum(String type) {
        BaseDto<Integer> dto = new BaseDto<>();
        try {

            final int pageNum = dao.getPageNum(type);
            dto.setCode(StatusCode.OK);
            dto.setMsg("OK");
            dto.setData(pageNum);
        } catch (DataAccessException e) {
            dto.setCode(StatusCode.DB_ERROR);
            dto.setMsg("error");
        }
        return dto;
    }

}
