package com.example.web.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface CrudService<T, ID> {
	Iterable<T> getAll();
    Optional<T> getById(ID id);
    boolean existsById(ID id);
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
