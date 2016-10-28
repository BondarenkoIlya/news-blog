package com.epam.ilya.dao;

import com.epam.ilya.model.Comment;

public class CommentDao extends DaoEntity implements Dao<Comment> {

    public CommentDao() throws DaoException {
    }

    @Override
    public Comment create(Comment comment) {
        return null;
    }

    @Override
    public Comment findById(int id) {
        return null;
    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void delete(Comment comment) {

    }
}
