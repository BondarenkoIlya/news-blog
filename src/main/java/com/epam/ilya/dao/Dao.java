package com.epam.ilya.dao;

/**
 * Class describes all working with database entities behaviour
 *
 * @param <T>
 */

public interface Dao<T> {

    /**
     * Method creates record in database by <T> object
     *
     * @param t with out id
     * @return created <T> object with id
     * @throws DaoException
     */

    T create(T t) throws DaoException;

    /**
     * Method finds record by id and picks <T> entity
     *
     * @param id for finding <T> object in database
     * @return picked entity
     * @throws DaoException
     */

    T findById(int id) throws DaoException;

    /**
     * Method updates object's record in database
     *
     * @param t object needs to be update
     * @throws DaoException
     */

    void update(T t) throws DaoException;

    /**
     * Method deletes correspondent record from base
     *
     * @param id of deleting entity
     * @throws DaoException
     */

    void delete(int id) throws DaoException;

}
