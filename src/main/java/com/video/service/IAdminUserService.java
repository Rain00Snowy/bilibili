package com.video.service;

import com.video.entity.Admin;
import com.video.entity.TUser;
import com.video.entity.dto.UserStateDTO;

import java.util.List;

public interface IAdminUserService {
    List<UserStateDTO> userList();

    UserStateDTO getUserById(Long id);

    void editUser(TUser tUser);

    void deleteUser(Long id);


    Admin login(Admin admin);

    List<UserStateDTO> getUserByName(String username);
}
