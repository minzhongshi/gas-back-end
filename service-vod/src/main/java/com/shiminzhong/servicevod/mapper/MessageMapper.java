package com.shiminzhong.servicevod.mapper;

import com.shiminzhong.servicevod.model.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
public interface MessageMapper extends BaseMapper<Message> {
    @Select("select * from message where site_id in (select site_id from point_supply_station where head_id = #{siteHeadId})")
    List<Message> siteMessages(@Param("siteHeadId") String siteHeadId);
}
