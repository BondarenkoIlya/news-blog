package com.epam.ilya.dao;

import com.epam.ilya.model.Comment;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static oracle.jdbc.internal.OracleStatement.ACTIVE;
import static oracle.jdbc.internal.OracleStatement.CLOSED;

public class CommentDao extends DaoEntity implements Dao<Comment> {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDao.class);

    private static final String INSERT_COMMENT = "INSERT INTO SYSTEM.COMMENT VALUES (NULL,?,?,?,?,1)";
    private static final String FIND_BY_ID = "SELECT * FROM SYSTEM.COMMENT WHERE ID=? AND ACTIVE=1";
    private static final String UPDATE_COMMENT = "UPDATE SYSTEM.COMMENT SET AUTHOR=?, date=? ,CONTENT=?, NEWS_ID=? ,ACTIVE=?  WHERE ID=?";

    public CommentDao() throws DaoException {
    }

    @Override
    public Comment create(Comment comment) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT, new String[]{"ID"})) {
            setCommentInPreparedStatement(comment, preparedStatement);
            preparedStatement.setInt(4, 1);//TODO Test news_id. Decide what to do with Comment entity field "News"
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                LOG.debug("Generated id is - {}", id);
                comment.setId(id);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot create statement for creating new news", e);
        }
        return comment;
    }

    @Override
    public Comment findById(int id) throws DaoException {
        Comment comment = new Comment();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment = pickCommentFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot get connection for finding by id", e);
        }
        return comment;
    }

    @Override
    public void update(Comment comment) throws DaoException {
        updateCommentWithStatus(comment,ACTIVE );//TODO Correct?
    }

    @Override
    public void delete(Comment comment) throws DaoException {
        updateCommentWithStatus(comment,CLOSED );
    }

    private void updateCommentWithStatus(Comment comment, int status) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)) {
            setCommentInPreparedStatement(comment, preparedStatement);
            preparedStatement.setInt(4, 1);//TODO Test news_id
            preparedStatement.setInt(5, status);
            preparedStatement.setInt(6, comment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot update news", e);
        }
    }

    private void setCommentInPreparedStatement(Comment comment, PreparedStatement preparedStatement) throws DaoException {
        try {
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setDate(2, new Date(comment.getDate().getMillis()));
            preparedStatement.setString(3, comment.getContent());
        } catch (SQLException e) {
            throw new DaoException("Cannot set comment in prepared statement", e);
        }
    }

    private Comment pickCommentFromResultSet(ResultSet resultSet) throws DaoException {
        Comment comment = new Comment();
        try {
            comment.setId(resultSet.getInt(1));
            comment.setAuthor(resultSet.getString(2));
            comment.setDate(new DateTime(resultSet.getDate(3)));
            comment.setContent(resultSet.getString(4));
        } catch (SQLException e) {
            throw new DaoException("Cannot pick comment from result set", e);
        }
        return comment;
    }
}
