package com.video.service;

import com.video.entity.TDanmu;

import java.util.List;

public interface IDanmuService {
    int sendDanmu(TDanmu tDanmu);

    List<TDanmu> getDanmuByVideoId(Long videoId);
}
