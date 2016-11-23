package com.epam.ilya.dao;

import com.epam.ilya.model.News;

import javax.persistence.Query;
import java.util.List;

/**
 * Class encapsulates all methods of manipulation with {@link News} in database
 *
 * @author Ilya_Bondarenko
 */
public class NewsDao extends AbstractDao implements Dao<News> {

    /**
     * {@inheritDoc}
     */
    @Override
    public News findById(int id) throws DaoException {
        rebootManager();
        News news = entityManager.find(News.class, id);
        if (news == null) {
            throw new DaoException("Do not find any news");
        }
        return news;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public News update(News news) throws DaoException {
        rebootManager();
        entityManager.getTransaction().begin();
        News newNews = entityManager.merge(news);
        entityManager.getTransaction().commit();
        return newNews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws DaoException {
        rebootManager();
        entityManager.getTransaction().begin();
        News news = entityManager.find(News.class, id);
        entityManager.remove(news);
        entityManager.getTransaction().commit();
    }

    /**
     * Method takes all news records from database
     *
     * @return List of news
     * @throws DaoException if arise any problem with database
     */
    public List<News> findAll() throws DaoException {
        rebootManager();
        Query query = entityManager.createQuery("FROM News", News.class);
        return query.getResultList();
    }

}