package com.shiminzhong.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shiminzhong.dictionary.entity.Locality;
import com.shiminzhong.dictionary.entity.ResponseResult;

public interface LocalityService extends IService<Locality> {
    /**
     * 查询所有分类的目录结构
     * @return
     */
    ResponseResult listCategory();
}
