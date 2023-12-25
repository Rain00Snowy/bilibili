package com.video.controller;

import com.video.entity.TUser;
import com.video.service.IFocusService;
import com.video.service.IUserService;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "focusUser", method = RequestMethod.POST)
    public MsgResponse focusVerify(HttpSession session, String focusedId) {
        TUser user = (TUser) session.getAttribute("user");
//        TUser user =new TUser();
//        user.setUserId(16L);
        if(user != null && focusedId != null && !focusedId.equals("")) {
            Long userId = user.getUserId();
            Long focusedIdLong = Long.parseLong(focusedId);
            return MsgResponse.success(focusService.addFocused(userId, focusedIdLong),null);

        }
        return MsgResponse.fail("关注失败");
    }


    @RequestMapping("focusedList")
    public MsgResponse focusedList(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
//        TUser user = new TUser();
//        user.setUserId(16L);
        if(user!=null) {
            Long userId = user.getUserId();
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
    @RequestMapping("fansList")
    public MsgResponse fansList(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
//        TUser user = new TUser();
//        user.setUserId(1L);
        if(user!=null) {
            Long userId = user.getUserId();
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
}
