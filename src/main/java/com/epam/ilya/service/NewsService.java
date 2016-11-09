package com.epam.ilya.service;

import com.epam.ilya.dao.AbstractDaoFactory;
import com.epam.ilya.dao.Dao;
import com.epam.ilya.dao.DaoException;
import com.epam.ilya.dao.DaoType;
import com.epam.ilya.dao.jdbc.CommentDao;
import com.epam.ilya.dao.jdbc.NewsDao;
import com.epam.ilya.model.Comment;
import com.epam.ilya.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class encapsulates all methods of manipulation with {@link Comment} and {@link News}
 *
 * @author Ilya_Bondarenko
 */

public class NewsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);
    private static final String ID_REGEX = "\\p{N}+";
    private static DaoType daoType = DaoType.JDBC;


    /**
     * Method gets list of all news
     *
     * @return list of news
     * @throws ServiceException
     */

    public List<News> getNewsList() throws ServiceException {
        List<News> newsList;
        try (AbstractDaoFactory daoFactory = AbstractDaoFactory.getDaoFactory(daoType)) {
            Dao<News> newsDao = daoFactory.getDao(News.class);
            newsList = newsDao.getNewsList();
        } catch (DaoException e) {
            LOGGER.error("Cannot create news dao for giving news list", e);
            throw new ServiceException("Cannot create news dao for giving news list", e);
        }
        return newsList;
    }


    /**
     * Method gets selected by id news
     *
     * @param id of needs news
     * @return news
     * @throws ServiceException
     */

    public News getNewsById(String id) throws ServiceException {
        News news = new News();
        try (AbstractDaoFactory daoFactory = AbstractDaoFactory.getDaoFactory(daoType)) {
            try {
                NewsDao newsDao = daoFactory.getDao(NewsDao.class);
                CommentDao commentDao = daoFactory.getDao(CommentDao.class);
                daoFactory.startTransaction();
                news = newsDao.findById(Integer.parseInt(id));
                List<Comment> comments = commentDao.getNewsComments(news);
                news.setComments(comments);
                daoFactory.commitTransaction();
            } catch (DaoException e) {
                daoFactory.rollbackTransaction();
                throw new ServiceException("Cannot create news dao for finding news by id", e);
            }
        } catch (DaoException e) {
            LOGGER.error("Cannot create news dao for finding news by id", e);
            throw new ServiceException("Cannot create dao factory", e);
        }
        return news;
    }

    /**
     * Method deletes selected by id news and all connected comments
     *
     * @param id of deleting news
     * @throws ServiceException
     */

    public void deleteNews(String id) throws ServiceException {
        try (AbstractDaoFactory daoFactory = AbstractDaoFactory.getDaoFactory(daoType)) {
            try {
                CommentDao commentDao = daoFactory.getDao(CommentDao.class);
                NewsDao newsDao = daoFactory.getDao(NewsDao.class);
                daoFactory.startTransaction();
                commentDao.deleteCommentsByNews(Integer.parseInt(id));
                newsDao.delete(Integer.parseInt(id));
                daoFactory.commitTransaction();
            } catch (DaoException e) {
                daoFactory.rollbackTransaction();
                throw new ServiceException("Cannot create dao for deactivating news", e);
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot create dao factory", e);
        }
    }

    /**
     * Method updates news if there was id or creates news one if there wasn't
     *
     * @param news editing news
     * @param id   of selected news
     * @return news
     * @throws ServiceException
     */

    public News updateOrCreateNewsById(News news, String id) throws ServiceException {
        if (id.matches(ID_REGEX)) {
            news.setId(Integer.parseInt(id));
        }
        try (AbstractDaoFactory daoFactory = AbstractDaoFactory.getDaoFactory(daoType)) {
            NewsDao newsDao = daoFactory.getDao(NewsDao.class);
            if (news.getId() != 0) {
                newsDao.update(news);
            } else {
                news = newsDao.create(news);
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update news", e);
        }
        return news;
    }

    /**
     * Method creates new {@link Comment} for selected by id {@link News}
     *
     * @param newComment needs to be created
     * @param newsId     of connected with comment news
     * @throws ServiceException
     */

    public void createCommentForNewsWithId(Comment newComment, String newsId) throws ServiceException {
        News news = new News();
        news.setId(Integer.parseInt(newsId));
        newComment.setNews(news);
        try (AbstractDaoFactory daoFactory = AbstractDaoFactory.getDaoFactory(daoType)) {
            CommentDao commentDao = daoFactory.getDao(CommentDao.class);
            commentDao.create(newComment);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create new comment", e);
        }
    }

    /**
     * Method deletes selected {@link Comment}
     *
     * @param id of comment that needs to be deleted
     * @throws ServiceException
     */

    public void deleteComment(String id) throws ServiceException {
        try (AbstractDaoFactory daoFactory = AbstractDaoFactory.getDaoFactory(daoType)){
            CommentDao commentDao = daoFactory.getDao(CommentDao.class);
            commentDao.delete(Integer.parseInt(id));
        } catch (DaoException e) {
            throw new ServiceException("Cannot deactivate comment", e);
        }
    }
}
