package com.video.controller;

import com.video.entity.TMessage;
import com.video.entity.TMsgtype;
import com.video.entity.TState;
import com.video.entity.TUser;
import com.video.service.IMessageService;
import com.video.service.IMsgTypeService;
import com.video.service.IStateService;
import com.video.service.IUserService;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Resource
    private IUserService userService;

    @Resource
    private IMsgTypeService msgTypeService;

    @Resource
    private IStateService stateService;

    @Resource
    private IMessageService messageService;

    @RequestMapping("getMsgListByType")
    @ResponseBody
    public MsgResponse getMsgListByType(HttpSession session,String msgTypeName) {

//        TUser user=new TUser();
//        user.setUserId(12L);测试
//        return MsgResponse.success("获取成功", messageService.findMsgByMsgType(user,msgTypeName));
//
        return MsgResponse.success("获取成功", messageService.findMsgByMsgType((TUser)session.getAttribute("user"),msgTypeName));
    }

    @RequestMapping("delMsgById")
    public MsgResponse delMsg(Long msgId) {
        return MsgResponse.success(messageService.deleteMsgById(msgId),null);
    }

    @RequestMapping("readMsg")
    public MsgResponse updateMsgState(Long msgId) {
        return MsgResponse.success(messageService.updateReadMessage(msgId, 7L), null);
    }

    @RequestMapping("sendMsg")
    public MsgResponse addLetter(HttpSession session, @RequestParam String msgTitle, @RequestParam String msgContext, @RequestParam Long receiveUserId, @RequestParam Long msgTypeId) {
        TUser sendUser = (TUser) session.getAttribute("user");
        TMessage msg = new TMessage();
        String addMsg = null;
        if(sendUser != null) {
            msg.setMsgTitle(msgTitle);
            msg.setMsgContext(msgContext);
            msg.setSendUser(sendUser);
            TUser recieveUser = userService.getUserByUserId(receiveUserId);
            if(recieveUser == null) {
                addMsg = "接收用户为空";
                return  MsgResponse.fail(addMsg);
            }
            if(recieveUser.getUserId().equals(sendUser.getUserId())) {
                return MsgResponse.fail("不可对自己私信");
            }
            msg.setReceiveUser(recieveUser);
            TMsgtype msgType = msgTypeService.findOneByMsgTypeId(msgTypeId);
            msg.setMsgType(msgType);
            TState msgState = stateService.getStateByStateId(6L);
            msg.setMsgState(msgState);
            addMsg = messageService.addMsg(msg);
            return MsgResponse.success(addMsg, null);
        }
        addMsg = "发送用户为空";
        return MsgResponse.fail(addMsg);
    }
}