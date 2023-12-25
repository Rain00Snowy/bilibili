package com.video.service.impl;

import com.video.entity.TVote;
import com.video.mapper.VoteLogMapper;
import com.video.mapper.VoteMapper;
import com.video.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteLogMapper voteLogMapper;

    @Override
    public int insert(TVote vote) {
        vote.setCreateTime(new Date());
        return voteMapper.insert(vote);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return voteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TVote> selectList(String voteName) {
        List<TVote> tVotes = voteMapper.selectList(voteName);
        for (TVote tVote : tVotes) {
            int count = voteLogMapper.getCountByVoteId(tVote.getId());
            tVote.setCount(count);
        }
        return tVotes;
    }

    @Override
    public TVote selectById(Long id) {
        return voteMapper.selectById(id);
    }
}
