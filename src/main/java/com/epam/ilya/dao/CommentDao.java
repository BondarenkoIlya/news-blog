package com.epam.ilya.dao;

import com.epam.ilya.model.Comment;
import com.epam.ilya.model.News;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends DaoEntity implements Dao<Comment> {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDao.class);

    private static final String INSERT_COMMENT = "INSERT INTO SYSTEM.\"COMMENT\" VALUES (NULL,?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM SYSTEM.\"COMMENT\" WHERE ID=?";
    private static final String UPDATE_COMMENT = "UPDATE SYSTEM.\"COMMENT\" SET AUTHOR=?, SYSTEM.NEWS.\"date\"=? ,CONTENT=?, NEWS_ID=? ,ACTIVE=?  WHERE ID=?";
    private static final String DELETE_COMMENT = "DELETE FROM SYSTEM.\"COMMENT\" WHERE ID=?";
    private static final String FIND_COMMENT_BY_NEWS = "SELECT * FROM SYSTEM.\"COMMENT\" WHERE NEWS_ID =? AND ACTIVE=1";
    private static final String DEACTIVATE_COMMENTS = "UPDATE SYSTEM.\"COMMENT\" SET ACTIVE=0 WHERE NEWS_ID=?";
    private static final String DEACTIVATE_COMMENT = "UPDATE SYSTEM.\"COMMENT\" SET ACTIVE=0 WHERE ID=?";

    public CommentDao() throws DaoException {
    }

    @Override
    public Comment create(Comment comment) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT, new String[]{"ID"})) {
            setCommentInPreparedStatement(comment, preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                LOG.debug("Generated id is - {}", id);
                comment.setId(id);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot create statement for creating new comment", e);
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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)) {
            setCommentInPreparedStatement(comment, preparedStatement);
            preparedStatement.setInt(6, comment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot update comment", e);
        }
    }

    @Override
    public void delete(Comment comment) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT)) {
            preparedStatement.setInt(1, comment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot delete comment", e);
        }
    }


    public List<Comment> getNewsComments(News news) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMMENT_BY_NEWS)) {
            preparedStatement.setInt(1,news.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                comments.add(pickCommentFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Cannot create statement for finding comments by news",e);
            throw new DaoException("Cannot create statement for finding comments by news", e);
        }
        return comments;
    }

    public void deactivateCommentsByNews(int newsId) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEACTIVATE_COMMENTS)) {
            preparedStatement.setInt(1,newsId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot deactivate news comments",e);
        }
    }

    public void deactivateComment(int id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEACTIVATE_COMMENT)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot deactivate comment",e);
        }
    }

    private void setCommentInPreparedStatement(Comment comment, PreparedStatement preparedStatement) throws DaoException {
        try {
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setDate(2, new Date(comment.getDate().getMillis()));
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setInt(4, comment.getNews().getId());
            preparedStatement.setInt(5, comment.getStatus());
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
            comment.setStatus(resultSet.getInt(6));
        } catch (SQLException e) {
            throw new DaoException("Cannot pick comment from result set", e);
        }
        return comment;
    }
}
