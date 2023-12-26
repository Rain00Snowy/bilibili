package com.video.controller;

import ch.qos.logback.classic.Logger;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.entity.Admin;
import com.video.entity.TUser;
import com.video.entity.dto.UserStateDTO;
import com.video.service.IAdminUserService;
import com.video.service.IUserService;
import com.video.util.Base64Util;
import com.video.util.MsgResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RequestMapping("admin")
@RestController
public class AdminUserController {
    StringBuffer imageUrl=new StringBuffer();
    private static final Logger log = (Logger) LoggerFactory.getLogger(AdminUserController.class);
    @Resource
    private IAdminUserService adminUserService;
    @Resource
    private IUserService userService;
    //管理员登录
    @RequestMapping("login")
    public MsgResponse login(Admin admin, HttpSession session) {
        Admin admin1 = adminUserService.login(admin);
        if (admin1 == null) {
            return MsgResponse.fail("密码错误");

        }
        session.setAttribute("admin", admin);
        return MsgResponse.success("登录成功", null);
    }
    //用户CRUD部分
    @RequestMapping("list")
    public List<UserStateDTO> userList() {
        return adminUserService.userList();
    }
    @RequestMapping("getUserById")
    public UserStateDTO getUserById(Long id) {
        return adminUserService.getUserById(id);
    }

    @PostMapping(value = "editUser")
    public void editUser(@RequestBody TUser tUser) {
        adminUserService.editUser(tUser);
    }

    @RequestMapping("deleteUser")
    public String deleteUser(Long id) {
        adminUserService.deleteUser(id);
        return "success";
    }
//    @PostMapping(value = "addUser")
//    public String addUser(TUser tUser) {
//        adminUserService.addUser(tUser);
//        return "success";
//    }

    @RequestMapping("getIcon")
    public MsgResponse getVideoImage(Long userId) throws IOException {
        try{
            String imgUrl=userService.getUserByUserId(userId).getIconUrl();
            return MsgResponse.success("获取成功", Base64Util.imageToBase64(imgUrl));
        }catch (Exception e)
        {
            e.printStackTrace();
            return MsgResponse.fail("获取失败");
        }
    }


    @GetMapping(value = "searchUser")
    public PageInfo<UserStateDTO> searchUser(int pageNum, int pageSize,String username) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //用户记录
        List<UserStateDTO> userStateDTOS = adminUserService.getUserByName(username);
        //获得 用户分页
        return new PageInfo<UserStateDTO>(userStateDTOS);
    }
    @GetMapping(value = "pageInfo")
    public PageInfo<UserStateDTO> getPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //用户记录
        List<UserStateDTO> userStateDTOS = adminUserService.userList();
        //获得 用户分页
        PageInfo<UserStateDTO> pageInfo = new PageInfo<UserStateDTO>(userStateDTOS);

        return pageInfo;
    }
    @RequestMapping("updateLevel")
    public String updateLevel(int level,Long id) {
        userService.updateLevel(level,id);
        return "200";
    }





}
