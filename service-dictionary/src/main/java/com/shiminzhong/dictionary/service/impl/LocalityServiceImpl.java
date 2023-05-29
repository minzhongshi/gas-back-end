package com.shiminzhong.dictionary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiminzhong.dictionary.entity.Dictionary;
import com.shiminzhong.dictionary.entity.Locality;
import com.shiminzhong.dictionary.entity.ResponseResult;
import com.shiminzhong.dictionary.mapper.DictionaryMapper;
import com.shiminzhong.dictionary.mapper.LocalityMapper;
import com.shiminzhong.dictionary.service.IDictionaryService;
import com.shiminzhong.dictionary.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocalityServiceImpl extends ServiceImpl<LocalityMapper, Locality> implements LocalityService {
    @Autowired
    private LocalityMapper localityMapper;

    @Override
    public ResponseResult listCategory() {
        return new ResponseResult(200,"查询成功", localityMapper.findCategoryList());
    }
}
