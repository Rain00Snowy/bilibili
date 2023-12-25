package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TUser;
import com.video.entity.dto.UserStateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
    int deleteByPrimaryKey(Long userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser queryByuserTelAndPwd(Map<Object, String> map);

    List<TUser> queryByuserTel(String userTel);

    int register(TUser tUser);

    void updateUserByUserId(TUser tUser);

    List<UserStateDTO> userList();

    UserStateDTO getUserById(@Param("id") Long id);

    int updateUserIcon(@Param("s") String s, @Param("userId") Long userId);

    List<UserStateDTO> getUserByName(@Param("username") String username);

    int updateLevel(@Param("level") Integer level, @Param("userId") Long userId);
    int updateUserFans(@Param("userId") Long userId);
}
