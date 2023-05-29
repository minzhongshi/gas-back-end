package com.shiminzhong.servicemqtt.device.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiminzhong.servicemqtt.device.entity.PointSupplyStation;
import com.shiminzhong.servicemqtt.device.entity.Region;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;
import com.shiminzhong.servicemqtt.device.mapper.RegionMapper;
import com.shiminzhong.servicemqtt.device.service.RegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {
    @Resource
    private RegionMapper regionMapper;

    @Override
    public ResponseResult Region(Region region){

        LambdaQueryWrapper<Region> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Region::getStandId,region.getStandId());

        List<Region> list = regionMapper.selectList(wrapper);
        if (!Objects.isNull(list)){
            return new ResponseResult(200,"查询成功",list);
        }
        return new ResponseResult(400,"没有找到记录");
    }

}
