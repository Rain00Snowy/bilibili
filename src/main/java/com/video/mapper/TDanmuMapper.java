package com.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.video.entity.TDanmu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface TDanmuMapper extends BaseMapper<TDanmu> {
    List<TDanmu> getDanmuByVideoId(Long videoId);
    int sendDanmu(TDanmu tDanmu);
}
