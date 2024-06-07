package com.backend.clinica.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T create(T t);

    void update(T t);

    void delete(Long id);
}
