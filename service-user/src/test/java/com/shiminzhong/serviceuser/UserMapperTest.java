package com.shiminzhong.serviceuser;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.mapper.AppUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserMapperTest {
//    @Resource
//    private UserMapper userMapper;

//    @Test
//    public void testGetAll(){
//        Page<User>page = new Page<>(1,2);
//        userMapper.selectPage(page,null);
//        System.out.println("总数:"+page.getTotal());
//        List<User>records = page.getRecords();
//        records.forEach(System.out::println);
//    }

//    @Test
//    public void testGetAll2(){
//        LambdaQueryWrapper<User>wrapper = new LambdaQueryWrapper<>(); //条件构造器
//        wrapper.gt(User::getAge,20);//大于20
//        wrapper.likeRight(User::getName,"Fu");//右模糊
//        wrapper.orderByDesc(User::getId); //降序
//
//        List<User>users= userMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }

}
