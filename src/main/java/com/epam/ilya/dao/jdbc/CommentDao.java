package com.epam.ilya.dao.jdbc;

import com.epam.ilya.dao.Dao;
import com.epam.ilya.dao.DaoException;
import com.epam.ilya.model.Comment;
import com.epam.ilya.model.News;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class encapsulates all methods of manipulation with {@link Comment} in database
 *
 * @author Ilya_Bondarenko
 */

public class CommentDao extends DaoEntity implements Dao<Comment> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsDao.class);

    private static final String INSERT_COMMENT = "INSERT INTO SYSTEM.\"COMMENT\" VALUES (NULL,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM SYSTEM.\"COMMENT\" WHERE ID=?";
    private static final String UPDATE_COMMENT = "UPDATE SYSTEM.\"COMMENT\" SET AUTHOR=?, SYSTEM.NEWS.\"date\"=? ,CONTENT=?, NEWS_ID=? WHERE ID=?";
    private static final String DELETE_COMMENT = "DELETE FROM SYSTEM.\"COMMENT\" WHERE ID=?";
    private static final String FIND_COMMENT_BY_NEWS = "SELECT * FROM SYSTEM.\"COMMENT\" WHERE NEWS_ID =?";
    private static final String DELETE_COMMENT_BY_NEWS = "DELETE FROM SYSTEM.\"COMMENT\" WHERE NEWS_ID=?";


    public CommentDao() throws DaoException {
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Comment create(Comment comment) throws DaoException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_COMMENT, new String[]{"ID"})) {
            setCommentInPreparedStatement(comment, preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                LOGGER.debug("Generated id is - {}", id);
                comment.setId(id);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot create statement for creating new comment", e);
        }
        return comment;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Comment findById(int id) throws DaoException {
        Comment comment = new Comment();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_BY_ID)) {
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

    /**
     * {@inheritDoc}
     */

    @Override
    public void update(Comment comment) throws DaoException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_COMMENT)) {
            setCommentInPreparedStatement(comment, preparedStatement);
            preparedStatement.setInt(5, comment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot update comment", e);
        }
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void delete(int id) throws DaoException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_COMMENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot delete comment", e);
        }
    }

    /**
     * Method takes all comment records connecting with selected news
     *
     * @param news contains list of comments
     * @return List of comments
     * @throws DaoException
     */

    public List<Comment> getNewsComments(News news) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_COMMENT_BY_NEWS)) {
            preparedStatement.setInt(1, news.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(pickCommentFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot create statement for finding comments by news", e);
            throw new DaoException("Cannot create statement for finding comments by news", e);
        }
        return comments;
    }

    /**
     * Method deletes all comments records connected with selected news
     *
     * @param newsId of news
     * @throws DaoException
     */

    public void deleteCommentsByNews(int newsId) throws DaoException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_COMMENT_BY_NEWS)) {
            preparedStatement.setInt(1, newsId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot create statement for deleting by news", e);
        }
    }

    private void setCommentInPreparedStatement(Comment comment, PreparedStatement preparedStatement) throws DaoException {
        try {
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setDate(2, new Date(comment.getDate().getMillis()));
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setInt(4, comment.getNews().getId());
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
