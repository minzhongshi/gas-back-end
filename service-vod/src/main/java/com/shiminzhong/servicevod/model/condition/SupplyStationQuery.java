package com.shiminzhong.servicevod.model.condition;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SupplyStationQuery对象", description = "")
public class SupplyStationQuery {

    @ApiModelProperty(value = "点供站id")
    @TableId(value = "stand_id", type = IdType.NONE)
    private String standId;

    @ApiModelProperty(value = "地区id")
    private String areaId;

    @ApiModelProperty(value = "站点名称")
    private String name;

    @ApiModelProperty(value = "状态 0:正常 1：异常 2：离线 ")
    private String state;
}
