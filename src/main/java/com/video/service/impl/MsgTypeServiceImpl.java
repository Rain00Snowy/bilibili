package com.video.service.impl;

import com.video.entity.TMsgtype;
import com.video.mapper.TMsgtypeMapper;
import com.video.service.IMsgTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgTypeServiceImpl implements IMsgTypeService {
    @Autowired
    private TMsgtypeMapper msgTypeMapper;

    @Override
    public TMsgtype findOneByMsgTypeId(Long msgTypeId) {
        return msgTypeMapper.selectByPrimaryKey(msgTypeId);
    }

    @Override
    public TMsgtype findByTypeName(String msgTypeName) {
        if(msgTypeName != null && !msgTypeName.equals("")) {
             TMsgtype msgType = msgTypeMapper.selectByTypeName(msgTypeName);
             return msgType;
        }
        return null;
    }
}
