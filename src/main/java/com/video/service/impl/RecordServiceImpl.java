package com.video.service.impl;

import com.video.entity.TVideo;
import com.video.entity.dto.UserStateDTO;
import com.video.enums.VIPEnum;
import com.video.mapper.TRecordMapper;
import com.video.mapper.TUserMapper;
import com.video.service.IRecordService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements IRecordService {

    @Resource
    private TUserMapper userMapper;

    @Resource
    private TRecordMapper recordMapper;

    @Override
    public List<TVideo> getAllRecordById(Long userId) {
        if (userMapper.selectByPrimaryKey(userId) != null) {
            List<TVideo> List = recordMapper.getRecordAll(userId);
            return List;
        }
        return null;
    }

    @Override
    public String deleteRecordById(Long userId, Long videoId) {
        if (userMapper.selectByPrimaryKey(userId) != null) {
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
                    recordMapper.insertOne(userId, videoId);
                    return "观看成功";
            } catch (Exception e) {
                return "观看失败";
            }
        }
        return "用户未登录";
    }
}
