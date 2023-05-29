package com.shiminzhong.servicevod.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("point_supply_station")
@ApiModel(value="PointSupplyStation对象", description="")
public class PointSupplyStation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "点供站id")
    @TableId(value = "stand_id", type = IdType.NONE)
    private String standId;

    @ApiModelProperty(value = "站点名称")
    private String name;

    @ApiModelProperty(value = "最近上传时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "异常信息")
    private String error;

    @ApiModelProperty(value = "状态 0:正常 1：异常 2：离线 ")
    private String state;

    @ApiModelProperty(value = "地区")
    private String area;

    @ApiModelProperty(value = "地区id")
    private String areaId;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "维度")
    private BigDecimal dimension;

    @ApiModelProperty(value = "负责人id")
    private String headId;

    @ApiModelProperty(value = "负责人名称")
    private String headName;

    @ApiModelProperty(value = "压力上限")
    private Integer pressureUppere;

    @ApiModelProperty(value = "储罐压力下限")
    private Integer pressureBelowe;

    @ApiModelProperty(value = "燃气浓度")
    private Integer gas;

    @ApiModelProperty(value = "汽化区燃气浓度")
    private Integer vaporizeGas;

    @ApiModelProperty(value = "瞬时流量上限")
    private Integer flowUppere;

    @ApiModelProperty(value = "瞬时流量下限")
    private Integer flowBelow;


}
