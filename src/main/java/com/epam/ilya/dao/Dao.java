package com.epam.ilya.dao;

public interface Dao<T> {

    T create(T t);

    T findById(int id);

    void update(T t);

    void delete(T t);

}
