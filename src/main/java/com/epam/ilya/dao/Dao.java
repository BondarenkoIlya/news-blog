package com.epam.ilya.dao;

/**
 * Class describes all working with database entities behaviour
 *
 * @param <T> type of managed entity
 */
public interface Dao<T> {

    /**
     * Method finds record by id and picks <T> entity
     *
     * @param id for finding <T> object in database
     * @return picked entity
     * @throws DaoException if arise any problem with database
     */
    T findById(int id) throws DaoException;

    /**
     * Method updates object's record in database or creates new record in case of doesn't find record with same id
     *
     * @param entity object needs to be update
     * @throws DaoException if arise any problem with database
     */
    T update(T entity) throws DaoException;

    /**
     * Method deletes correspondent record from base
     *
     * @param id of deleting entity
     * @throws DaoException if arise any problem with database
     */
    void delete(int id) throws DaoException;

}
