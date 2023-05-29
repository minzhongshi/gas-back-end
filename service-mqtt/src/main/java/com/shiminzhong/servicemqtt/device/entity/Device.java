package com.shiminzhong.servicemqtt.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * TODO
 *
 * @author 史明忠
 * @since 2023/5/10
 */
@Data
@TableName("region_parameter")
@AllArgsConstructor
@NoArgsConstructor
public class Device {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("stand_id")
  private String stand_id;

  @TableField("device_name")
  private String device_name;

  @TableField("state")
  private String state;

  @TableField("price")
  private String price;

}
