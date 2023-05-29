package com.shiminzhong.dictionary.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@TableName("locality")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true) // 调用Setting方法后 回传对象
public class Locality {
    /**
     * 分类地区code
     */
    @TableId(value = "code")
    private String code;

    /**
     * 分类栏目名称
     */
    private String name;

    /**
     * 父级栏目
     */
    private String parent_code;

}
