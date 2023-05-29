package com.shiminzhong.servicevod.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
public class MediaController {

    //    @Resource
    //    private VodService vodService;
    //
    //    /**
    //     * @Description:    上传视频到阿里云
    //     * @Author: 		shiminzhong
    //     * @date 			2021/12/1 23:32
    //     **/
    //    @PostMapping("/uploadAlyVideo")
    //    public R uploadAlyVideo(MultipartFile file){
    //        //返回上传视频id
    //        String videoId = vodService.uploadVideoAly(file);
    //        return R.ok().data("videoId",videoId);
    //    }

}
