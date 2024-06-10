package com.backend.clinica.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(Long id);

    T create(T t);

    T update(T t);

    T delete(Long id);
}
