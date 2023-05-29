package com.shiminzhong.servicevod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiminzhong.servicevod.model.dto.RegionExtParameter;
import com.shiminzhong.servicevod.model.entity.RegionParameter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
public interface RegionParameterMapper extends BaseMapper<RegionParameter> {

    @Select("select a.stand_id,a.equipment_area as equipmentArea," +
            "b.device_name as deviceName,b.state,b.price from region a,region_parameter b where a.stand_id = #{standId} and a.stand_id = b.stand_id")
    List<RegionExtParameter> regionDevices(@Param("standId") String standId);

}
