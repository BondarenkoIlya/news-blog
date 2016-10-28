package com.epam.ilya.dao;

public interface Dao<T> {

    T create(T t) throws DaoException;

    T findById(int id) throws DaoException;

    void update(T t) throws DaoException;

    void delete(T t) throws DaoException;

}
