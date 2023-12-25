package com.video.service;

import com.video.entity.TMessage;
import com.video.entity.TUser;

import java.util.List;

public interface IMessageService {
    String addMsg(TMessage msg);

    List<TMessage> findMsgByMsgType(TUser user, String msgTypeName);

    String updateReadMessage(Long msgId, Long i);

    String deleteMsgById(Long msgId);

    int msgCount(Long userId);
}
