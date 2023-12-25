package com.video.controller;

import com.mysql.cj.Session;
import com.video.entity.TUser;
import com.video.entity.TVideo;
import com.video.entity.TVote;
import com.video.entity.TVoteLog;
import com.video.entity.dto.ResultDTO;
import com.video.service.*;
import com.video.util.Base64Util;
import com.video.util.MsgResponse;
import com.video.util.ValidateCode;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class VideoUserController {
    @Resource
    private IUserService userService;

    @Resource
    private IMessageService messageService;


    @RequestMapping("UEditorUser")
    public String UEditorUser(HttpSession session, TUser tUser, String preEncryptedProblem) {
        if (tUser.getEncryptedProblem().equals(preEncryptedProblem)) {
            return "errorMsg 当前密保不是最新密保";
        }
        session.removeAttribute("user");
        tUser.setIconUrl(userService.getUserByUserId(tUser.getUserId()).getIconUrl());
        session.setAttribute("user", tUser);
        System.out.println("tuser" + tUser);
        userService.updateUserByUserId(tUser);
        return "更改成功";
    }

    @RequestMapping("getUserById/{userId}")
    public MsgResponse getUserById(@PathVariable Long userId){
        try {
            TUser user=userService.getUserByUserId(userId);
            if(user==null)
                return MsgResponse.fail("用户不存在");
            return MsgResponse.success("获取成功",user);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }

    /*---------上传头像--------*/
    @ResponseBody
    @RequestMapping("upload")
    public MsgResponse upload(MultipartFile file, HttpSession session) {

        try {
            String workplace = System.getProperty("user.dir");
            //获取文件后缀
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            TUser user = (TUser) session.getAttribute("user");
//            TUser user =new TUser();测试
//            user.setUserId(23L);
            String fileName = "icon" + user.getUserId() + suffix;
            File newFile = new File(workplace + "/src/main/resources/static/uimages/" + fileName);
            userService.updateUserIcon("/static/uimages/" + fileName, user.getUserId());
            //写入文件
            file.transferTo(newFile);
            System.out.println("success");
            return MsgResponse.success("上传成功", null);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgResponse.fail("上传失败");
        }
    }

    //    /*---------获取用户头像--------*/
    @RequestMapping("getIcon")
    public MsgResponse getIcon(HttpSession session){
        try {
            TUser user = (TUser) session.getAttribute("user");
//            TUser user = new TUser();
            //测试
//            user.setIconUrl("/static/uimages/icon23.png");
            return MsgResponse.success("获取成功",Base64Util.imageToBase64(user.getIconUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }


    /*----------用户注册-----------*/
    @RequestMapping("userRegister")
    @ResponseBody
    public ResultDTO register(@RequestParam String userTel,
                              @RequestParam String password) {
        TUser user=new TUser();
        user.setUserTel(userTel);
        user.setPassword(password);
        user.setStateId(1);
        user.setFanNum(0);
        ResultDTO res = userService.register(user);
        return res;
    }

    /*----------用户登录-----------*/
    @RequestMapping("userLogin")
    @ResponseBody
    public ResultDTO login(String userTel, String password, String aCode, HttpSession session) {
        ResultDTO<TUser> res = userService.login(userTel, password);
        if (res.getResult()) {
            //判断验证码
//            session.setAttribute("ValidateCode","asd");
            String randomCode = (String) session.getAttribute(ValidateCode.RANDOMCODEKEY);
            if (!aCode.equalsIgnoreCase(randomCode)) {
                //equalsIgnoreCase方法忽略大小写判断
                res.setMessage("验证码错误");
                res.setResult(false);
                return res;
            }
            int messageCount = messageService.msgCount(res.getData().getUserId());
            System.out.println(messageCount);
            session.setAttribute("messageCount", "(" + messageCount + ")");
            session.setAttribute("user", res.getData());
        }
        return res;
    }

    /*----------获取验证码-----------*/
    @RequestMapping("getKey")
    public void Img(HttpSession session, HttpServletResponse resp) {
        resp.setContentType("image/jpeg");
        //设置响应头，不缓存这个响应的内容,确保每次请求都会得到一个新的验证码
        resp.setHeader("Pragma", "No-cache");
        //引用工具类中的方法，绘制验证码图片
        ValidateCode code = new ValidateCode();
        code.getValidateCode(session, resp);//调用绘制验证码的方法，绘制图片
    }

    @RequestMapping("updateLevel")
    @ResponseBody
    public String updateLevel(TUser tUser) {
        userService.updateLevel(tUser.getLevel(), tUser.getUserId());
        return "200";
    }
}
