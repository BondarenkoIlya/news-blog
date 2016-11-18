package com.epam.ilya.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class DaoEntity {
    protected static final String UNIT_NAME = "task1";
    protected EntityManager entityManager;

    protected void rebootManager() {
        entityManager = Persistence.createEntityManagerFactory(UNIT_NAME).createEntityManager();
    }
}
