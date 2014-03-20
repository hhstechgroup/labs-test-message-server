package com.engagepoint.university.messaging.service;


import com.engagepoint.university.messaging.dto.base.BaseDTO;

import java.util.List;

public interface GenericService<T extends BaseDTO> {
    public T getById(Long id);
    public List<T> getAll();
    public void save(T t);
    public void update(T t);
    public void delete(Long id);
    public void delete(T t);
}
