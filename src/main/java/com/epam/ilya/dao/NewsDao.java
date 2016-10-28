package com.epam.ilya.dao;

import com.epam.ilya.model.News;

public class NewsDao extends DaoEntity implements Dao<News> {

    public NewsDao() throws DaoException {
    }

    @Override
    public News create(News news) {
        return null;
    }

    @Override
    public News findById(int id) {
        return null;
    }

    @Override
    public void update(News news) {

    }

    @Override
    public void delete(News news) {

    }
}
