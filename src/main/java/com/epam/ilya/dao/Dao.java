package com.epam.ilya.dao;

public interface Dao<T> {

    T create(T t) throws DaoException;

    T findById(int id) throws DaoException;

    void update(T t) throws DaoException;

    void delete(int news) throws DaoException;

}
