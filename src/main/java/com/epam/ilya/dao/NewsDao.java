package com.epam.ilya.dao;

import com.epam.ilya.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Class encapsulates all methods of manipulation with {@link News} in database
 *
 * @author Ilya_Bondarenko
 */

public class NewsDao extends DaoEntity implements Dao<News> {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDao.class);

    @Override
    public News findById(int id) throws DaoException {
        rebootManager();
        return entityManager.find(News.class, id);
    }

    @Override
    public News update(News news) throws DaoException {
        rebootManager();
        entityManager.getTransaction().begin();
        News newNews = entityManager.merge(news);
        entityManager.getTransaction().commit();
        return newNews;
    }

    @Override
    public void delete(int id) throws DaoException {
        rebootManager();
        entityManager.getTransaction().begin();
        News news = entityManager.find(News.class, id);
        entityManager.remove(news);
        entityManager.getTransaction().commit();
    }

    public List<News> findAll() throws DaoException {
        rebootManager();
        Query query = entityManager.createQuery("FROM News",News.class);
        return query.getResultList();
    }
}