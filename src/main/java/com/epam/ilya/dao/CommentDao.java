package com.epam.ilya.dao;

import com.epam.ilya.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class encapsulates all methods of manipulation with {@link Comment} in database
 *
 * @author Ilya_Bondarenko
 */

public class CommentDao extends DaoEntity implements Dao<Comment> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsDao.class);

    @Override
    public Comment findById(int id) throws DaoException {
        rebootManager();
        return entityManager.find(Comment.class, id);
    }

    @Override
    public Comment update(Comment comment) throws DaoException {
        rebootManager();
        entityManager.getTransaction().begin();
        Comment newComment = entityManager.merge(comment);
        entityManager.getTransaction().commit();
        return newComment;
    }

    @Override
    public void delete(int id) throws DaoException {
        rebootManager();
        entityManager.getTransaction().begin();
        Comment comment = entityManager.find(Comment.class, id);
        entityManager.remove(comment);
        entityManager.getTransaction().commit();
    }
}
