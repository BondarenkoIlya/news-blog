package com.epam.ilya.dao;

import com.epam.ilya.model.News;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.ilya.model.BaseEntity.ACTIVE;

public class NewsDao extends DaoEntity implements Dao<News> {
    private static final Logger LOG = LoggerFactory.getLogger(NewsDao.class);

    private static final String INSERT_NEWS = "INSERT INTO SYSTEM.NEWS VALUES (NULL ,?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM SYSTEM.NEWS WHERE ID=?";
    private static final String UPDATE_NEWS = "UPDATE SYSTEM.NEWS SET TITLE=?, SYSTEM.NEWS.\"date\"=?,BRIEF=?,CONTENT=? ,ACTIVE=? WHERE ID=?";
    private static final String DELETE_NEWS = "DELETE FROM SYSTEM.NEWS WHERE ID=?";
    private static final String FIND_ALL_NEWS = "SELECT * FROM SYSTEM.NEWS WHERE ACTIVE=?";


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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEWS)) {
            LOG.debug("Start update news - {}", news);
            setNewsInPreparedStatement(news, preparedStatement);
            LOG.debug("set in statement ");
            preparedStatement.setInt(6, news.getId());
            preparedStatement.execute();
            LOG.debug(" After execute");
        } catch (SQLException e) {
            throw new DaoException("Cannot update news", e);
        }
    }

    @Override
    public void delete(News news) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NEWS)) {
            preparedStatement.setInt(1, news.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot create statement for deleting news", e);
        }
    }


    public List<News> getActiveNewsList() throws DaoException {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_NEWS)) {
            preparedStatement.setInt(1,ACTIVE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                newsList.add(pickNewsFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot create statement for finding all news", e);
        }
        return newsList;
    }

    private void setNewsInPreparedStatement(News news, PreparedStatement preparedStatement) throws DaoException {
        try {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setDate(2, new Date(news.getDate().getMillis()));
            preparedStatement.setString(3, news.getBrief());
            preparedStatement.setString(4, news.getContent());
            preparedStatement.setInt(5, news.getStatus());
        } catch (SQLException e) {
            throw new DaoException("Cannot set news in statement", e);
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
            news.setStatus(resultSet.getInt(6));
        } catch (SQLException e) {
            throw new DaoException("Cannot pick news from result set", e);
        }
        return news;
    }
}
