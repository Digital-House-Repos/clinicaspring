package com.backend.clinica.service;

import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.exception.ResourceNotFoundException;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(Long id) throws ResourceNotFoundException;

    T create(T t) throws ResourceNotFoundException, EntityAlreadyExistsException;

    T update(T t) throws ResourceNotFoundException, EntityAlreadyExistsException;

    T delete(Long id) throws ResourceNotFoundException;

    Long countAll();
}
