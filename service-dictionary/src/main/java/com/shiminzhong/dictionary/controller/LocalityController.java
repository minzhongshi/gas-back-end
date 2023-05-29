package com.shiminzhong.dictionary.controller;

import com.shiminzhong.dictionary.entity.Locality;
import com.shiminzhong.dictionary.entity.ResponseResult;
import com.shiminzhong.dictionary.service.LocalityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;

@RestController
@RequestMapping("/locality")
public class LocalityController {

    @Resource
    private LocalityService localityService;
    /**
     * 查询地区所有分类的目录结构
     * @return
     */

    @GetMapping("/list")
    public ResponseResult getCategoryList() {
        return localityService.listCategory();
    }
}
