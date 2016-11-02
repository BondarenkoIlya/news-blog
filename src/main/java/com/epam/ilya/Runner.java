package com.epam.ilya;

import com.epam.ilya.dao.CommentDao;
import com.epam.ilya.dao.DaoException;
import com.epam.ilya.dao.NewsDao;
import com.epam.ilya.model.Comment;
import com.epam.ilya.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Runner {
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        try {
            CommentDao commentDao = new CommentDao();
            NewsDao newsDao = new NewsDao();
            News news = newsDao.findById(49);
            List<Comment> newsComments = commentDao.getNewsComments(news);
            if (newsComments.isEmpty()){
                LOG.debug("Have no comment for this news");
            }else {
                for (Comment comment: newsComments) {
                    LOG.debug("News comment - {}",comment);
                }
            }
        } catch (DaoException e) {
            LOG.error("Cannot create dao");
        }
    }
}
