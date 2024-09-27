package com.example.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, Integer> {
    UserToken findByToken(String token);
    List<UserToken> findByUserId(Integer userId);
}
