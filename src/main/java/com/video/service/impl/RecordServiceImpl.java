package com.video.service.impl;

import com.video.entity.TVideo;
import com.video.entity.dto.UserStateDTO;
import com.video.enums.VIPEnum;
import com.video.mapper.TRecordMapper;
import com.video.mapper.TUserMapper;
import com.video.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TRecordMapper recordMapper;

    @Override
    public List<TVideo> getAllRecord(Long userId) {
        if (userMapper.selectById(userId) != null) {
            List<TVideo> List = recordMapper.getRecordAll(userId);
            return List;
        }
        return null;
    }

    @Override
    public String deleteRecordById(Long userId, Long videoId) {
        if (userMapper.selectById(userId) != null) {
            try {
                if (recordMapper.selectOne(userId, videoId) != null) {
                    recordMapper.deleteRecordByVideoId(userId, videoId);
                    return "记录删除成功";
                } else {
                    return "观看记录不存在";
                }
            } catch (Exception e) {
                return "记录删除失败";
            }
        }
        return "用户未登录";
    }

    @Override
    public String addRecord(Long userId, Long videoId) {
        if (userMapper.selectByPrimaryKey(userId) != null) {
            try {
                if (recordMapper.selectOne(userId, videoId) == null) {
                    recordMapper.insertOne(userId, videoId);
                    //判断用户观看次数与等级的关系
                    int count = recordMapper.selectCountByUserId(userId);
                    int level = VIPEnum.getLevelByCount(count);
                    UserStateDTO userById = userMapper.getUserById(userId);
                    if(null != userById && null != userById.getLevel() && userById.getLevel() < level){
                        //更新用户等级
                        userMapper.updateLevel(level,userId);
                    }
                    return "观看成功";
                } else {
                    return "记录已存在";
                }

            } catch (Exception e) {
                return "观看失败";
            }
        }
        return "用户未登录";
    }
}
