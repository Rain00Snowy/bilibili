package com.video.service.impl;

import com.video.entity.TVideotype;
import com.video.mapper.TVideotypeMapper;
import com.video.service.IVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoTypeServiceImpl implements IVideoTypeService {

    @Autowired
    private TVideotypeMapper videotypeMapper;

    @Override
    public TVideotype getVideoTypeByTypeName(String typeName) {
        return videotypeMapper.getVideoTypeByTypeName(typeName);
    }

    @Override
    public List<TVideotype> getVideoTypeAll() {
        return videotypeMapper.getVideoTypeAll();
    }

    @Override
    public TVideotype getVideoTypeByVideoTypeId(Long videotypeId) {
        return videotypeMapper.selectByPrimaryKey(videotypeId);

    }
}
