package com.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.entity.TVideotype;
import com.video.service.IAdminVideoTypeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminVideoTypeController {

    @Resource
    private IAdminVideoTypeService adminVideoTypeService;

    @GetMapping( "searchVideoType")
    public PageInfo<TVideotype> searchVideoType(int pageNum, int pageSize, String typeName) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<TVideotype> videoTypes = adminVideoTypeService.getVideoTypeByName(typeName);
        //获得 视频分页
        PageInfo<TVideotype> vidoeTypeInfo = new PageInfo<TVideotype>(videoTypes);
        return vidoeTypeInfo;
    }
    @GetMapping( "categoryPageInfo")
    public PageInfo<TVideotype> categoryPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<TVideotype> videoTypes = adminVideoTypeService.categoryList();
        //获得 视频分页
        PageInfo<TVideotype> vidoeTypeInfo = new PageInfo<TVideotype>(videoTypes);
        return vidoeTypeInfo;
    }

    @RequestMapping("addcategory")
    public String addVideoType(@RequestBody TVideotype videotype) {
        boolean flag = adminVideoTypeService.addVideoType(videotype);
        return flag ? "200" : "404";
    }

    @RequestMapping("updatecategory")
    public String updateVideoType(@RequestBody TVideotype videotype) {
        boolean flag = adminVideoTypeService.updateVideoType(videotype);
        return flag ? "200" : "404";
    }

    @RequestMapping("getCategory")
    public TVideotype getCategory(Long id) {
        return adminVideoTypeService.getCategoryById(id);
    }

    @RequestMapping("deleteCategory")
    public String deleteCategory( Long id) {
        boolean flag = adminVideoTypeService.deleteCategoryById(id);
        return flag ? "success" : "fail";
    }

}
