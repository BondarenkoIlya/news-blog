package com.epam.ilya.service;

import com.epam.ilya.dao.CommentDao;
import com.epam.ilya.dao.DaoException;
import com.epam.ilya.dao.NewsDao;
import com.epam.ilya.model.Comment;
import com.epam.ilya.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NewsService {
    private static final Logger LOG = LoggerFactory.getLogger(NewsService.class);

    public List<News> getActiveNewsList() throws ServiceException {
        List<News> newsList;
        try {
            NewsDao newsDao = new NewsDao();
            newsList = newsDao.getActiveNewsList();
        } catch (DaoException e) {
            LOG.error("Cannot create news dao for giving news list",e);
            throw new ServiceException("Cannot create news dao for giving news list", e);
        }
        return newsList;
    }


    public News getNewsById(String id) throws ServiceException {
        News news = new News();
        try {
            NewsDao newsDao = new NewsDao();
            CommentDao commentDao = new CommentDao();
            news = newsDao.findById(Integer.parseInt(id));
            List<Comment> comments = commentDao.getNewsComments(news);
            news.setComments(comments);
        } catch (DaoException e) {
            LOG.error("Cannot create news dao for finding news by id",e);
            throw new ServiceException("Cannot create news dao for finding news by id",e);
        }
        return news;
    }
}
