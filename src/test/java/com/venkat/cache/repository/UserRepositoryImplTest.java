package com.venkat.cache.repository;


import com.venkat.cache.model.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.embedded.RedisServerBuilder;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryImplTest {

    private static redis.embedded.RedisServer redisServer;


    @Autowired
    private UserRepository userRepository;


    @BeforeClass
    public static void startRedisServer() throws IOException {
        redisServer = new RedisServerBuilder().port(6379).setting("maxmemory 256M").build();
        redisServer.start();
    }

    @AfterClass
    public static void stopRedisServer() throws IOException {
        redisServer.stop();
    }

    @Test
    public void saveTest(){
        User user = new User();
        user.setId("1");
        user.setName("Venkatram");
        user.setSalary(123456789L);
        userRepository.save(user);
        User newUser = userRepository.findById("1");
        System.out.println("New Uer is " + newUser);
        assertEquals(user.getName(), newUser.getName());
    }

    @Test
    public void saveTest2(){
        User user = new User();
        user.setId("2");
        user.setName("Chand");
        user.setSalary(123L);
        userRepository.save(user);
        User newUser = userRepository.findById("2");
        System.out.println("New Uer is " + newUser);
        assertEquals(user.getName(), newUser.getName());
    }
}
