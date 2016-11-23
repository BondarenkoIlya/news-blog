package com.epam.ilya.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Class describes common behavior of all dao classes
 *
 * @author Ilya_Bondarenko
 */
public abstract class AbstractDao {

    protected static final String UNIT_NAME = "task1";

    protected EntityManager entityManager;

    protected void rebootManager() {
        entityManager = Persistence.createEntityManagerFactory(UNIT_NAME).createEntityManager();
    }

}
