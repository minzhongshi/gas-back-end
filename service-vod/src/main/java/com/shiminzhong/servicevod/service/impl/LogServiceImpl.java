package com.shiminzhong.servicevod.service.impl;

import com.shiminzhong.servicevod.model.entity.Log;
import com.shiminzhong.servicevod.mapper.LogMapper;
import com.shiminzhong.servicevod.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
