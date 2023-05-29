package com.shiminzhong.servicevod.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LogInfo {
    @ApiModelProperty(value = "点供站id")
    private String siteId;

    @ApiModelProperty(value = "点供站名称")
    private String siteName;

    @ApiModelProperty(value = "操作内容")
    private String operationContent;

    @ApiModelProperty(value = "操作人员")
    private String operationName;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "视频地址")
    private String video;
}
