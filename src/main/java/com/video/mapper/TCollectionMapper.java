package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TCollection;
import com.video.entity.TVideo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TCollectionMapper extends BaseMapper<TCollection> {
    int deleteByPrimaryKey(Long collectionId);

    int insert(TCollection record);

    int insertSelective(TCollection record);

    TCollection selectByPrimaryKey(Long collectionId);

    int updateByPrimaryKeySelective(TCollection record);

    int updateByPrimaryKey(TCollection record);

    TCollection selectOne(@Param("userId") Long userId, @Param("videoId") Long videoId);

    @Insert("insert into t_collection(user_id, video_Id) values(#{userId}, #{videoId})")
    int insertOne(@Param("userId") Long userId, @Param("videoId") Long videoId);

    List<TVideo> getCollettionAll(Long userId);

    int deleteCollectionByVideoId(@Param("userId") Long userId, @Param("videoId") Long videoId);

    void deleteByUserId(Long id);
}
