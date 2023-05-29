package com.shiminzhong.servicemqtt.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("statistics")
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    @TableId(value = "standId")
    @TableField("standId")
    private String standId;

    @TableField("name")
    private String name;

    @TableField("used")
    private Float used;

    @TableField("turnover")
    private Float turnover;

}
