package com.video.service;

import com.video.entity.TMsgtype;

public interface IMsgTypeService {
    TMsgtype findByTypeName(String msgTypeName);

    TMsgtype findOneByMsgTypeId(Long msgTypeId);
}
