package com.shiminzhong.dictionary.entity;

import lombok.Data;

import java.util.List;

@Data
public class LocalityChildVo {
    //ID
    private String value;

    // 父级分类名称
    private String text;

    // 子分类
    private List<LocalityAreaVo> children;
}
