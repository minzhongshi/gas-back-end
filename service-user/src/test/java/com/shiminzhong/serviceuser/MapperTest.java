package com.shiminzhong.serviceuser;

import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.mapper.AppUserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class MapperTest {
    @Resource
    private AppUserMapper appUserMapper;

    @Test
    public void TestBC()
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
    }
    @Test
    public void testUserMapper() {
        List<AppUser> users = appUserMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
