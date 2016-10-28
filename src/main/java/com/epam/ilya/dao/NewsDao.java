package com.epam.ilya.dao;

import com.epam.ilya.model.News;

import java.sql.*;

public class NewsDao extends DaoEntity implements Dao<News> {
    private static final String INSERT_NEWS = "INSERT INTO SYSTEM.NEWS VALUES (NULL ,?,?,?,?,1)";


    public NewsDao() throws DaoException {
    }

    @Override
    public News create(News news) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setDate(2, new Date(news.getDate().getMillis()));
            preparedStatement.setString(3, news.getBrief());
            preparedStatement.setString(4, news.getContent());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                news.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot create statement for creating new news", e);
        }
        return news;
    }

    @Override
    public News findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(News news) throws DaoException {

    }

    @Override
    public void delete(News news) throws DaoException {

    }
}
