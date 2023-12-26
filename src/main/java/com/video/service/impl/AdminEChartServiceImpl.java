package com.video.service.impl;

import com.video.mapper.AdminMapper;
import com.video.service.IAdminEChartService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminEChartServiceImpl implements IAdminEChartService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public List<String> getFilmData() {
        return adminMapper.getFilmData();
    }

    @Override
    public List<Integer> getData() {
        return adminMapper.getData();
    }
}
