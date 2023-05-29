package com.shiminzhong.servicemqtt.device.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiminzhong.servicemqtt.device.entity.Device;
import com.shiminzhong.servicemqtt.device.mapper.DeviceMapper;
import com.shiminzhong.servicemqtt.device.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author 史明忠
 * @since 2023/5/10
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper,Device> implements DeviceService {

}
