package com.video.service.impl;

import com.video.entity.Admin;
import com.video.entity.TUser;
import com.video.entity.dto.UserStateDTO;
import com.video.mapper.*;
import com.video.service.IAdminUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Resource
    private TUserMapper userMapper;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private TVideoMapper videoMapper;

    @Resource
    private TCollectionMapper collectionMapper;

    @Resource
    private TRecordMapper recordMapper;
    @Resource
    private TAdvertisementMapper advertisementMapper;


    @Override
    public List<UserStateDTO> userList() {
        return userMapper.userList();
    }

    @Override
    public UserStateDTO getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void editUser(TUser tUser){
        userMapper.updateUserByUserId(tUser);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteByPrimaryKey(id);
        videoMapper.deleteByUserId(id);
        collectionMapper.deleteByUserId(id);
        recordMapper.deleteByUserId(id);
    }


    @Override
    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }

    @Override
    public List<UserStateDTO> getUserByName(String username) {
        return userMapper.getUserByName(username);
    }



}
