package com.shiminzhong.dictionary.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shiminzhong.dictionary.entity.Dictionary;
import com.shiminzhong.dictionary.entity.ResponseResult;
import com.shiminzhong.dictionary.mapper.DictionaryMapper;
import com.shiminzhong.dictionary.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiminzhong
 * @since 2023-05-19
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public ResponseResult list(String dictType) {

        LambdaUpdateWrapper<Dictionary> wrapper = new LambdaUpdateWrapper<>(); //条件构造器
        wrapper.eq(Dictionary::getDictType,dictType);
        List<Dictionary> list = dictionaryMapper.selectList(wrapper);
        if (!list.isEmpty()) {
            return new ResponseResult<>(200,"查询成功",list);
        }
        return new ResponseResult<>(400,"查询出错");
    }
}
