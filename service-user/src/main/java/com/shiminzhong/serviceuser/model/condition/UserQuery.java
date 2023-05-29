package com.shiminzhong.serviceuser.model.condition;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserQuery对象", description = "")
public class UserQuery extends PageQuery implements Serializable {

    @ApiModelProperty(value = "工号")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "性别 0:男  1：女")
    private Integer gender;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "状态 0：启用 1：禁用")
    private Integer status;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "是否为负责人")
    private Integer head;

    @ApiModelProperty(value = "地区code")
    private String regionId;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "操作时间,区间起始",example = "2023-05-15 20:57:00")
    private String operationStartTime;

    @ApiModelProperty(value = "操作时间,区间结束",example = "2023-05-15 20:58:00")
    private String operationEndTime;
}
