package com.video.controller;

import com.video.service.IAdminEChartService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminEChartController {
    @Resource
    private IAdminEChartService adminEChartService;

    @RequestMapping("getFilmData")
    @ResponseBody
    public List<String> getFilmData() {
        return adminEChartService.getFilmData();
    }

    @RequestMapping("getData")
    @ResponseBody
    public List<Integer> getData() {
        return adminEChartService.getData();
    }
}
