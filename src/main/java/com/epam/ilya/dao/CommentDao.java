package com.epam.ilya.dao;

import com.epam.ilya.model.Comment;

public class CommentDao extends DaoEntity implements Dao<Comment> {

    public CommentDao() throws DaoException {
    }

    @Override
    public Comment create(Comment comment) throws DaoException {
        return null;
    }

    @Override
    public Comment findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(Comment comment) throws DaoException {

    }

    @Override
    public void delete(Comment comment) throws DaoException {

    }
}
