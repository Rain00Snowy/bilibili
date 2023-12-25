package com.video.service;

import com.video.entity.TVoteLog;

import java.util.List;

public interface VoteLogService {

    int insert(TVoteLog voteLog);

    List<TVoteLog> selectList(TVoteLog voteLog);
}
