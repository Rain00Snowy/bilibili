package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TMessage;
import com.video.entity.TMsgtype;
import com.video.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TMessageMapper extends BaseMapper<TMessage> {
    int deleteByPrimaryKey(Long msgId);

    int insertMsg(TMessage record);

    int insertSelective(TMessage record);

    TMessage selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(TMessage record);

    int updateByPrimaryKey(TMessage record);

    List<TMessage> selectMsgByMsgType(@Param("user") TUser user, @Param("msgType") TMsgtype msgType);

    int updateMessageState(@Param("msgId") Long msgId, @Param("stateId") Long stateId);

    @Select("select count(*) from t_message where msg_receive_user_id = #{userId} and msg_state_id=6")
    int msgCount(Long userId);
}
