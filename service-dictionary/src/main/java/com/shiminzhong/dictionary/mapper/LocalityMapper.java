package com.shiminzhong.dictionary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiminzhong.dictionary.entity.Locality;
import com.shiminzhong.dictionary.entity.LocalityParentVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalityMapper extends BaseMapper<Locality> {
    /**
     * 查询所有分类的目录结构
     * @return
     */
    List<LocalityParentVo> findCategoryList();
}
