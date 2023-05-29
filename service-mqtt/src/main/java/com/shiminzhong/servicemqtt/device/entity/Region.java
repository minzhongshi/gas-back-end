package com.shiminzhong.servicemqtt.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("region")
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("stand_id")
    private String standId;

    @TableField("equipment_area")
    private String equipmentArea;//storageTank:储罐区 flowmeter:流量计区

    @TableField("error")
    private String error;

    @TableField("state")
    private String state;
}
