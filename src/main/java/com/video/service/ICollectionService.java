package com.video.service;

import com.video.entity.TVideo;

import java.util.List;

public interface ICollectionService {
    String addCollection(Long userId, Long videoId);

    String vertifyIfCollection(Long userId, Long videoId);

    List<TVideo> getAllCollection(Long userId);

    String deleteCollection(Long userId, Long videoId);
}
