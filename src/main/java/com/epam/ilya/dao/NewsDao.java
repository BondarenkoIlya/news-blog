package com.epam.ilya.dao;

import com.epam.ilya.model.News;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static oracle.jdbc.internal.OracleStatement.ACTIVE;
import static oracle.jdbc.internal.OracleStatement.CLOSED;

public class NewsDao extends DaoEntity implements Dao<News> {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDao.class);

    private static final String INSERT_NEWS = "INSERT INTO SYSTEM.NEWS VALUES (NULL ,?,?,?,?,1)";
    private static final String FIND_BY_ID = "SELECT * FROM SYSTEM.NEWS WHERE ID=?";
    private static final String UPDATE_NEWS = "UPDATE SYSTEM.NEWS SET TITLE=?, date=?,BRIEF=?,CONTENT=? ,ACTIVE=? WHERE ID=?";


    public NewsDao() throws DaoException {
    }

    @Override
    public News create(News news) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS, new String[]{"ID"})) {
            setNewsInPreparedStatement(news, preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                LOG.debug("Generated id is - {}", id);
                news.setId(id);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot create statement for creating new news", e);
        }
        return news;
    }

    @Override
    public News findById(int id) throws DaoException {
        News news = new News();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                news = pickNewsFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot get connection for finding by id", e);
        }
        return news;
    }

    @Override
    public void update(News news) throws DaoException {
        updateNewsWithStatus(news, ACTIVE);
    }

    @Override
    public void delete(News news) throws DaoException {
        updateNewsWithStatus(news, CLOSED);
    }

    private void setNewsInPreparedStatement(News news, PreparedStatement preparedStatement) throws DaoException {
        try {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setDate(2, new Date(news.getDate().getMillis()));
            preparedStatement.setString(3, news.getBrief());
            preparedStatement.setString(4, news.getContent());
        } catch (SQLException e) {
            throw new DaoException("Cannot set news in statement",e);
        }
    }

    private News pickNewsFromResultSet(ResultSet resultSet) throws DaoException {
        News news = new News();
        try {
            news.setId(resultSet.getInt(1));
            news.setTitle(resultSet.getString(2));
            news.setDate(new DateTime(resultSet.getDate(3)));
            news.setBrief(resultSet.getString(4));
            news.setContent(resultSet.getString(5));
        } catch (SQLException e) {
            throw new DaoException("Cannot pick news from result set", e);
        }
        return news;
    }

    private void updateNewsWithStatus(News news, int status) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEWS)) {
            setNewsInPreparedStatement(news, preparedStatement);
            preparedStatement.setInt(5, status);
            preparedStatement.setInt(6, news.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot update news", e);
        }
    }
}
