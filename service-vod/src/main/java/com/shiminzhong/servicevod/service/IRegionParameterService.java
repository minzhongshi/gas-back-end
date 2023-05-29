package com.shiminzhong.servicevod.service;

import com.shiminzhong.servicevod.model.dto.RegionExtParameter;
import com.shiminzhong.servicevod.model.entity.RegionParameter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
public interface IRegionParameterService extends IService<RegionParameter> {

    List<RegionExtParameter> regionDevices(String standId);
}
