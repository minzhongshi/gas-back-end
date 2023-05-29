package com.shiminzhong.servicevod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiminzhong.servicevod.config.FsConfig;
import com.shiminzhong.servicevod.mapper.LogMapper;
import com.shiminzhong.servicevod.mapper.MessageMapper;
import com.shiminzhong.servicevod.model.ResponseResult;
import com.shiminzhong.servicevod.model.condition.LogQuery;
import com.shiminzhong.servicevod.model.entity.Log;
import com.shiminzhong.servicevod.model.entity.Message;
import com.shiminzhong.servicevod.model.entity.PointSupplyStation;
import com.shiminzhong.servicevod.service.ILogService;
import com.shiminzhong.servicevod.service.IMessageService;
import com.shiminzhong.servicevod.service.IPointSupplyStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@RestController
@RequestMapping("/message")
@Api(tags = "MessageController")
public class MessageController {

    @Autowired
    private ILogService logService;

    @Autowired
    private IMessageService messageService;

    @Resource
    private LogMapper logMapper;

    @Resource
    private MessageMapper messageMapper;

    @Autowired
    private IPointSupplyStationService pointSupplyStationService;

    @PostMapping("/record")
    @ApiOperation(value = "日志", httpMethod = "POST")
    public ResponseResult recordLog(@RequestBody Log log) {
        Assert.state(!ObjectUtils.isEmpty(log), "参数错误！");
        PointSupplyStation supplyStation = pointSupplyStationService.getById(log.getSiteId());
        Assert.state(!ObjectUtils.isEmpty(supplyStation), "点供站不存在！");
        log.setArea(supplyStation.getArea());
        log.setOperationTime(LocalDateTime.now());
        boolean result = logService.save(log);
        return new ResponseResult(result ? 1 : 0);
    }



    @PostMapping("/view")
    @ApiOperation(value = "日志查询", httpMethod = "POST")
    public ResponseResult logQuery(@RequestBody LogQuery logQuery) {
        Assert.state(!ObjectUtils.isEmpty(logQuery), "参数错误！");
        Page<Log>page = new Page<>(logQuery.getPageNumber(),logQuery.getPageSize());
        LambdaQueryWrapper<Log> queryWrapper = Wrappers.lambdaQuery(Log.class)
                .like(StringUtils.isNotBlank(logQuery.getOperationName()), Log::getOperationName, logQuery.getOperationName())
                .like(StringUtils.isNotBlank(logQuery.getSiteName()), Log::getSiteName, logQuery.getSiteName())
                .gt(StringUtils.isNotBlank(logQuery.getOperationStartTime()), Log::getOperationTime, logQuery.getOperationStartTime())
                .lt(StringUtils.isNotBlank(logQuery.getOperationEndTime()), Log::getOperationTime, logQuery.getOperationEndTime());
        return new ResponseResult(logMapper.selectPage(page,queryWrapper));
    }

    @PostMapping("/log/delete")
    @ApiOperation(value = "日志删除", httpMethod = "POST")
    public ResponseResult logDelete(String id) {
        Assert.state(StringUtils.isNotBlank(id), "参数错误！");
        Integer de = logMapper.deleteById(id);
        return de==1? new ResponseResult(200,"删除成功",de) : new ResponseResult(400,"删除失败",de);
    }

    @PostMapping("/push")
    @ApiOperation(value = "推送", httpMethod = "POST")
    public ResponseResult push(@RequestBody Message message) {
        Assert.state(!ObjectUtils.isEmpty(message), "参数错误！");
        message.setTime(LocalDateTime.now());
        boolean result = messageService.save(message);
        return new ResponseResult(result ? 1 : 0);
    }

    @PostMapping("/see")
    @ApiOperation(value = "消息查看", httpMethod = "POST")
    public ResponseResult msgQuery(String id) {
        Assert.state(StringUtils.isNotBlank(id), "参数错误！");
        return new ResponseResult(messageService.siteMessages(id));
    }

    @PostMapping("/list")
    @ApiOperation(value = "消息条数", httpMethod = "POST")
    public ResponseResult msgList() {
        QueryWrapper<Message> queryWrapper=new QueryWrapper();
        Long count = messageMapper.selectCount(queryWrapper);
        return new ResponseResult(200,"查询成功",count);
    }

    @PostMapping("/vedio")
    @ApiOperation(value = "视频上传", httpMethod = "POST")
    public ResponseResult msgVidio(MultipartFile vide) throws IOException {
        Assert.state(vide != null, "参数错误！");

        File storePath = new File(FsConfig.VEDIO_STOREAGE_PATH, FsConfig.FS_CONTEXT_PATH);
        if (!storePath.exists()) {
            storePath.mkdirs();
        }

        String originName = vide.getOriginalFilename();
        File storedFile = new File(storePath.getAbsolutePath(), originName);
        vide.transferTo(storedFile);
        return new ResponseResult("/fs/" + originName);
    }

}
