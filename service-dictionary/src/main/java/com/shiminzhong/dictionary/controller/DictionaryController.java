package com.shiminzhong.dictionary.controller;

import com.shiminzhong.dictionary.entity.Dictionary;
import com.shiminzhong.dictionary.entity.ResponseResult;
import com.shiminzhong.dictionary.service.IDictionaryService;
import com.shiminzhong.dictionary.service.impl.DictionaryServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shiminzhong
 * @since 2023-05-19
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Resource
    private IDictionaryService iDictionaryService;

    //字典列表查询
    @GetMapping("/list/{dictType}")
    public ResponseResult stateRenew(@PathVariable("dictType") String dictType) {

        return iDictionaryService.list(dictType);
    }
}
