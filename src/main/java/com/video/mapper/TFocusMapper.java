package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TFocus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TFocusMapper extends BaseMapper<TFocus> {
    int deleteByPrimaryKey(Long focusId);

    int insert(TFocus record);

    int insertSelective(TFocus record);

    TFocus selectByPrimaryKey(Long focusId);

    int updateByPrimaryKeySelective(TFocus record);

    int updateByPrimaryKey(TFocus record);

    int selectOneVerify(TFocus focus);

    List<Long> selectFocusedsId(Long userId);
    List<Long> selectFansId(Long userId);

    TFocus isFocused(@Param("userId") Long userId, @Param("focusedId") Long focusedId);

}
