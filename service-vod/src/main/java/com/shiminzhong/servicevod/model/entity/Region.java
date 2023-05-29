package com.shiminzhong.servicevod.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("region")
@ApiModel(value="Region对象", description="")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "点供站id")
    private String standId;

    @ApiModelProperty(value = "设备区域名称 storageTank:储罐区 flowmeter:流量计区")
    @TableField("equipment_area")
    private String equipmentArea;

    @ApiModelProperty(value = "异常信息")
    @TableField("error")
    private String error;

    @ApiModelProperty(value = "状态 0：正常 1：离线 2：异常")
    @TableField("state")
    private String state;


}
