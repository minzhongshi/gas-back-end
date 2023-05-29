package com.shiminzhong.serviceuser;


import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.mapper.AppUserMapper;
import com.shiminzhong.serviceuser.user.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ServiceUserApplicationTests {

    @Resource
    private AppUserMapper appUserMapper;

    //注入方式
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void TestBC()
    {
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void testUserMapper() {
        List<AppUser>users = appUserMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Resource
    private MenuMapper menuMapper;

    @Test
    public void testSelect(){
        List<String>list = menuMapper.selectPermsByUserId("202302310001");
        System.out.println(list);
    }

}
