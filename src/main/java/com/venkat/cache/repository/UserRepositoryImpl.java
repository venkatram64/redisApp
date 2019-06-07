package com.venkat.cache.repository;

import com.venkat.cache.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private RedisTemplate<String, User> redisTemplate;

    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        this.hashOperations.put("USER", user.getId(), user);
    }

    @Override
    public Map<String,User> findAll() {
        return this.hashOperations.entries("USER");
    }

    @Override
    public void update(User user) {
        this.hashOperations.put("USER", user.getId(), user);
    }

    @Override
    public void delete(String id) {
        this.hashOperations.delete("USER", id);
    }

    @Override
    public User findById(String id){
        return (User)this.hashOperations.get("USER",id);
    }
}
