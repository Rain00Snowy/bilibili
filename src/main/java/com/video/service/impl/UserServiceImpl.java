package com.video.service.impl;

import com.video.entity.TUser;
import com.video.entity.dto.ResultDTO;
import com.video.mapper.TUserMapper;
import com.video.service.IUserService;
import com.video.util.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private TUserMapper userMapper;

    @Override
    public ResultDTO<TUser> login(String userTel, String password) {
        Map<Object, String> map = new HashMap<>();
        map.put("userTel", userTel);
        map.put("password", password);
        TUser user = userMapper.queryByuserTelAndPwd(map);
        //判断user是否为空
        ResultDTO resultDTO = null;

        if (user == null) {
            resultDTO = ResultUtil.failResult(user, "手机号或密码为错误");
        } else {
            resultDTO = ResultUtil.successResult(user, "登录成功");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO register(TUser tUser) {
        ResultDTO res = new ResultDTO();

        tUser.setRegisterDate(new Date());
        if (!queryByuserTel(tUser.getUserTel())) {
            res.setResult(false);
            res.setMessage("用户已存在");
            return res;
        }
        int c = userMapper.register(tUser);
        
        if(c == 0) {
            res.setResult(false);
            res.setMessage("注册失败");
        }else {
            res.setResult(true);
            res.setMessage("注册成功");
        }
        return res;

    }

    @Override
    public int updateUserIcon(String s, Long userId) {
        return userMapper.updateUserIcon(s, userId);
    }

    @Override
    public TUser getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUserByUserId(TUser tUser) {
        userMapper.updateUserByUserId(tUser);
    }

    @Override
    public boolean queryByuserTel(String userTel) {
        List<TUser> userList = userMapper.queryByuserTel(userTel);
        return userList.size() == 0;
    }

    @Override
    public int updateLevel(Integer level, Long userId) {
        return userMapper.updateLevel(level,userId);
    }
}
