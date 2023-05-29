package com.shiminzhong.serviceuser.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiminzhong.serviceuser.user.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    //自定义方法查权限
    List<String> selectPermsByUserId(String userid);
}
