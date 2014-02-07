package com.engagepoint.university.messaging.dao;

import java.util.List;

public interface GenericDAO<T> {
    public T getById(int id);
    public List<T> getAll();
    public void save(T t);
    public void update(T t);
    public void delete(int id);
    public void delete(T t);
}
