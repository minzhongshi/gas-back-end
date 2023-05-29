package com.shiminzhong.servicevod.service.impl;

import com.shiminzhong.servicevod.model.dto.RegionExtParameter;
import com.shiminzhong.servicevod.model.entity.RegionParameter;
import com.shiminzhong.servicevod.mapper.RegionParameterMapper;
import com.shiminzhong.servicevod.service.IRegionParameterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@Service
public class RegionParameterServiceImpl extends ServiceImpl<RegionParameterMapper, RegionParameter> implements IRegionParameterService {

    @Override
    public List<RegionExtParameter> regionDevices(String standId){
        return baseMapper.regionDevices(standId);
    }
}
