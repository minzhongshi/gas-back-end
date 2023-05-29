package com.shiminzhong.servicemqtt.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("point_supply_station")
@AllArgsConstructor
@NoArgsConstructor
public class PointSupplyStation {

    @TableId(value = "stand_id", type = IdType.NONE)
    private String standId;

    private String name;

    private LocalDateTime time;

    private String error;

    private String state;


    private String area;

    private Integer areaId;

    private BigDecimal longitude;

    private BigDecimal dimension;

    private String headId;

    private String headName;

    private Integer pressureUppere;

    private Integer pressureBelowe;

    private Integer gas;

    private Integer vaporizeGas;

    private Integer flowUppere;

    private Integer flowBelow;

}
