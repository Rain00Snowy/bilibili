package com.video.service.impl;

import com.video.entity.TDanmu;
import com.video.mapper.TDanmuMapper;
import com.video.service.IDanmuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DanmuServiceImpl implements IDanmuService {
    @Resource
    TDanmuMapper tDanmuMapper;
    @Override
    public int sendDanmu(TDanmu tDanmu) {
        return tDanmuMapper.sendDanmu(tDanmu);
    }

    @Override
    public List<TDanmu> getDanmuByVideoId(Long videoId) {
        return tDanmuMapper.getDanmuByVideoId(videoId);
    }
}
