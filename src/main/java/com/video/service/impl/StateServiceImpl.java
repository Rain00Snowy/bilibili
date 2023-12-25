package com.video.service.impl;

import com.video.entity.TState;
import com.video.mapper.TStateMapper;
import com.video.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements IStateService {

    @Autowired
    private TStateMapper stateMapper;

    @Override
    public TState getStateByStateId(Long stateId) {
        return stateMapper.selectByPrimaryKey(stateId);
    }
}
