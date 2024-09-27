package com.example.web.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, Integer> {
    UserToken findByToken(String token);
}
