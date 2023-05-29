package com.shiminzhong.servicevod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiminzhong.servicevod.model.entity.PointSupplyStation;
import com.shiminzhong.servicevod.mapper.PointSupplyStationMapper;
import com.shiminzhong.servicevod.service.IPointSupplyStationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@Service
public class PointSupplyStationServiceImpl extends ServiceImpl<PointSupplyStationMapper, PointSupplyStation> implements IPointSupplyStationService {

    //    @Autowired
    //    private PointSupplyStationMapper pointSupplyStationMapper;
    //
    //    @Override
    //    public List<PointSupplyStation> supplyStationList(SupplyStationQuery supplyStationQuery) {
    //        pointSupplyStationMapper.selectList()
    //        return null;
    //    }
}
