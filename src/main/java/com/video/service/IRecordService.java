package com.video.service;

import com.video.entity.TVideo;

import java.util.List;

public interface IRecordService {
    String addRecord(Long userId, Long videoId);

    List<TVideo> getAllRecordById(Long userId);

    String deleteRecordById(Long userId, Long videoId);
}
