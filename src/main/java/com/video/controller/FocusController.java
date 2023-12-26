package com.video.controller;

import com.video.entity.TUser;
import com.video.service.IFocusService;
import com.video.service.IUserService;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("focus")
public class FocusController {

    @Resource
    private IUserService userService;

    @Resource
    private IFocusService focusService;

    @Resource
    MessageController messageController;

    @RequestMapping("focusUser")
    @ResponseBody
    public MsgResponse focusVerify(@RequestParam Long fanId,
                                   @RequestParam Long focusedId) {
//        TUser fan = userService.getUserByUserId(fanId);
//        TUser user =new TUser();
//        user.setUserId(16L);
        if(fanId != null && focusedId != null && !focusedId.equals("")) {
//            Long userId = user.getUserId();
            userService.updateUserFans(focusedId);//使被关注者粉丝+1
            //发送关注消息
            messageController.addLetter(fanId,
                    "系统消息",
                    "用户  "+userService.getUserByUserId(fanId).getUserName()+"  关注了你",
                    focusedId,
                    2l);
            return MsgResponse.success(focusService.addFocused(fanId, focusedId),null);
        }
        return MsgResponse.fail("关注失败");
    }


    @RequestMapping("/focusedList/{userId}")
    public MsgResponse focusedList(@PathVariable Long userId) {
        if(userId!=null) {
            List<Long> userFocusList = focusService.getUserFocusList(userId);
            List<TUser> focusList= new ArrayList<>();
            for(Long focusId : userFocusList) {
                focusList.add(userService.getUserByUserId(focusId));
            }

            return MsgResponse.success("成功", focusList);
        }else {
            return MsgResponse.fail("参数错误");
        }
    }
    @RequestMapping("/fansList/{userId}")
    public MsgResponse fansList(@PathVariable Long userId) {
        //修改成通过用户id查找粉丝列表
//        TUser user = (TUser) session.getAttribute("user");
//        TUser user = new TUser();
//        user.setUserId(1L);
        if(userId!=null) {
//            Long userId = user.getUserId();
            List<Long> userFocusList = focusService.getUserFansList(userId);
            List<TUser> focusList= new ArrayList<>();
            for(Long focusId : userFocusList) {
                focusList.add(userService.getUserByUserId(focusId));
            }

            return MsgResponse.success("成功", focusList);
        }else {
            return MsgResponse.fail("参数错误");
        }
    }
    @RequestMapping("/isFocused")
    public Boolean isFocused( Long userId, Long focusedId) {

        return focusService.isFocused(userId,focusedId);
    }
}
