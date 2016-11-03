package com.epam.ilya.dao;

import com.epam.ilya.model.News;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Real implementation called.
 */


public class NewsDaoIntegrationTest {
    @Ignore
    @Test
    public void create() throws Exception {
        News news = getTestNews();
        NewsDao newsDao = new NewsDao();
        newsDao.create(news);
        assertTrue(news.getId() > 0);
    }

    @Ignore
    @Test
    public void findById() throws Exception {
        News news = getTestNews();
        news.setContent("New test");
        NewsDao newsDao = new NewsDao();
        news = newsDao.create(news);
        News foundNews = newsDao.findById(news.getId());
        assertTrue(news.getTitle().equals(foundNews.getTitle()));
    }

    @Ignore
    @Test
    public void update() throws Exception {
        News news = getTestNews();
        NewsDao newsDao = new NewsDao();
        news = newsDao.create(news);
        news.setTitle("New test");
        newsDao.update(news);
        News foundNews = newsDao.findById(news.getId());
        assertTrue(news.getTitle().equals(foundNews.getTitle()));
    }

    @Ignore
    @Test
    public void delete() throws Exception {
        News news = getTestNews();
        NewsDao newsDao = new NewsDao();
        newsDao.delete(news.getId());
        News foundNews = newsDao.findById(news.getId());
        assertTrue(foundNews.getTitle() == null);
    }

    private News getTestNews() {
        News news = new News();
        news.setTitle("test");
        news.setBrief("test");
        news.setContent("test");
        return news;
    }

}