package com.shiminzhong.servicevod.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数名称")
    @TableField("device_name")
    private String deviceName;

    @ApiModelProperty(value = "状态 0：正常 1：离线 2：异常")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "参数值")
    @TableField("price")
    private String price;

}
