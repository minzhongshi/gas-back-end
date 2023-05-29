package com.shiminzhong.servicevod.model.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceModifyInfo {

    @ApiModelProperty(value = "点供站id")
    private String standId;

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
