package com.shiminzhong.dictionary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author shiminzhong
 * @since 2023-05-19
 */
@Data
@TableName("dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典内容名称
     */
    private String dictLabel;

    /**
     * 是否高亮
     */
    private String isDefault;

    /**
     * 是否启用
     */
    private String status;

    private Long value;
}
