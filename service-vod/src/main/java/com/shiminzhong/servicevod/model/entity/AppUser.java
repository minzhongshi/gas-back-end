package com.shiminzhong.servicevod.model.entity;

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
 * @since 2023-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("app_user")
@ApiModel(value="AppUser对象", description="")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工号")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "性别 0:男  1：女")
    private Integer gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "家庭住址")
    private String address;

    @ApiModelProperty(value = "注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态 0：启用 1：禁用")
    private Integer status;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "是否为地区负责人 0：是 1：不是")
    private Integer head;

    @ApiModelProperty(value = "区域id")
    private Integer regionId;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "删除标记")
    private Integer deleted;

    @ApiModelProperty(value = "乐观锁")
    private Integer version;


}
