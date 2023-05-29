package com.shiminzhong.dictionary.service;

import com.shiminzhong.dictionary.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiminzhong.dictionary.entity.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shiminzhong
 * @since 2023-05-19
 */
public interface IDictionaryService extends IService<Dictionary> {

     ResponseResult list(String dictType);
}
