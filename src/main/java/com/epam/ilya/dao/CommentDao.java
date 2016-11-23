package com.epam.ilya.dao;

import com.epam.ilya.model.Comment;

/**
 * Class encapsulates all methods of manipulation with {@link Comment} in database
 *
 * @author Ilya_Bondarenko
 */
public class CommentDao extends AbstractDao implements Dao<Comment> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Comment findById(int id) throws DaoException {
        rebootManager();
        return entityManager.find(Comment.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Comment update(Comment comment) throws DaoException {
        rebootManager();
        entityManager.getTransaction().begin();
        Comment newComment = entityManager.merge(comment);
        entityManager.getTransaction().commit();
        return newComment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws DaoException {
        rebootManager();
        entityManager.getTransaction().begin();
        Comment comment = entityManager.find(Comment.class, id);
        entityManager.remove(comment);
        entityManager.getTransaction().commit();
    }
}
