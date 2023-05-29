package com.shiminzhong.servicevod.model.condition;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value="LogQuery对象", description="")
public class LogQuery implements Serializable {

    @ApiModelProperty(value = "点供站id")
    private String siteId;

    @ApiModelProperty(value = "点供站名称")
    private String siteName;

    @ApiModelProperty(value = "操作人员")
    private String operationName;

    @ApiModelProperty(value = "操作时间,区间起始",example = "2023-05-15 20:57:00")
    private String operationStartTime;

    @ApiModelProperty(value = "操作时间,区间结束",example = "2023-05-15 20:58:00")
    private String operationEndTime;

    @ApiModelProperty(value = "页码")
    private Integer pageNumber;

    @ApiModelProperty(value = "条数")
    private Integer pageSize;
}
