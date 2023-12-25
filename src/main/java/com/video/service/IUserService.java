package com.video.service;

import com.video.entity.TUser;
import com.video.entity.dto.ResultDTO;

public interface IUserService {
    ResultDTO<TUser> login(String userTel, String password);

    boolean queryByuserTel(String userTel);

    ResultDTO register(TUser tUser);

    void updateUserByUserId(TUser tUser);

    TUser getUserByUserId(Long userId);

    int updateUserIcon(String s, Long userId);

    int updateLevel(Integer level,Long userId);
}
