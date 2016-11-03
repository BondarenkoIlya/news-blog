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

    public List<News> getNewsList() throws ServiceException {
        List<News> newsList;
        try {
            NewsDao newsDao = new NewsDao();
            newsList = newsDao.getNewsList();
        } catch (DaoException e) {
            LOG.error("Cannot create news dao for giving news list", e);
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
            LOG.error("Cannot create news dao for finding news by id", e);
            throw new ServiceException("Cannot create news dao for finding news by id", e);
        }
        return news;
    }

    public void deleteNews(String id) throws ServiceException {
        try {
            CommentDao commentDao = new CommentDao();
            NewsDao newsDao = new NewsDao();
            commentDao.deleteCommentsByNews(Integer.parseInt(id));
            newsDao.delete(Integer.parseInt(id));

        } catch (DaoException e) {
            throw new ServiceException("Cannot create dao for deactivating news", e);
        }
    }

    public News updateOrCreateNews(News news) throws ServiceException {
        try {
            NewsDao newsDao = new NewsDao();
            if (news.getId()!=0){
                newsDao.update(news);
            }else {
                news = newsDao.create(news);
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update news", e);
        }
        return news;
    }

    public Comment createCommentForNewsWithId(Comment newComment, String news_id) throws ServiceException {
        News news = new News();
        news.setId(Integer.parseInt(news_id));
        newComment.setNews(news);
        try {
            CommentDao commentDao = new CommentDao();
            newComment = commentDao.create(newComment);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create new comment", e);
        }
        return newComment;
    }

    public void deleteComment(String id) throws ServiceException {
        try {
            CommentDao commentDao = new CommentDao();
            commentDao.delete(Integer.parseInt(id));
        } catch (DaoException e) {
            throw new ServiceException("Cannot deactivate comment", e);
        }
    }
}
