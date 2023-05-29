package com.shiminzhong.servicevod.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("log")
@ApiModel(value="Log对象", description="")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(value = "点供站id")
    private String siteId;

    @ApiModelProperty(value = "点供站名称")
    private String siteName;

    @ApiModelProperty(value = "操作内容")
    private String operationContent;

    @ApiModelProperty(value = "操作人员")
    private String operationName;

    @ApiModelProperty(value = "操作时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationTime;

    @ApiModelProperty(value = "操作地区",hidden = true)
    private String area;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "视频地址")
    private String video;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

}
