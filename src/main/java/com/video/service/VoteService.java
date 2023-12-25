package com.video.service;

import com.video.entity.TVote;

import java.util.List;

public interface VoteService {
    int deleteByPrimaryKey(Long id);

    int insert(TVote vote);

    List<TVote> selectList(String voteName);

    TVote selectById(Long id);
}
