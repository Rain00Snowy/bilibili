package com.video.service.impl;

import com.video.entity.TFocus;
import com.video.mapper.TFocusMapper;
import com.video.service.IFocusService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FocusServiceImpl implements IFocusService {

    @Resource
    private TFocusMapper focusMapper;

    @Override
    public String focusVerify(Long userId, Long focusedIdLong) {
        TFocus focus = new TFocus();
        focus.setUserId(userId);
        focus.setFocusId(focusedIdLong);
        int flag = focusMapper.selectOneVerify(focus);
        if(flag == 0)
            return "未关注";
        return "已关注";
    }

    @Override
    public List<Long> getUserFocusList(Long userId) {
        return focusMapper.selectFocusedsId(userId);
    }
    @Override
    public List<Long> getUserFansList(Long userId){return focusMapper.selectFansId(userId);}
    @Override
    public String addFocused(Long userId, Long focusedIdLong) {
        //不可对自己关注
        if(userId.equals(focusedIdLong)) {
            return "不可对自己进行关注";
        }
        TFocus focus = new TFocus();
        focus.setUserId(userId);
        focus.setFocusedId(focusedIdLong);
        int flag = focusMapper.selectOneVerify(focus);
        if(flag == 0) {
            try {
                if(focusVerify(userId,focusedIdLong)=="未关注") {
                    focusMapper.insertSelective(focus);
                    return "关注成功";
                }
                else return "关注失败";
            } catch (Exception e) {
                System.out.println(e);
                return "关注失败";
            }
        }
        return "已关注";
    }
    @Override
    public boolean isFocused(Long userId, Long focusedId){
        TFocus tFocus=focusMapper.isFocused(userId,focusedId);
        if(tFocus==null)
            return false;
        else return true;
    };

}
