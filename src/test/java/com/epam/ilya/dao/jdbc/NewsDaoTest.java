package com.epam.ilya.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewsDaoTest {

    @Mock
    DataSource mockDataSource;

    @Mock
    Connection mockConn;

    @Mock
    PreparedStatement mockPreparedStmnt;

    @Mock
    ResultSet mockResultSet;
    int userId = 100;

    @Before
    public void setUp() throws SQLException {
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString(),(String[]) anyObject())).thenReturn(mockPreparedStmnt);
        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
        when(mockPreparedStmnt.execute()).thenReturn(Boolean.TRUE);
        when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(mockResultSet.getInt(1)).thenReturn(userId);
    }

    @Test
    public void shouldCreateNewNewsRecordInBaseAndReturnId() throws Exception {
        NewsDao newsDao = new NewsDao();
        newsDao.setConnection(mockConn);
        verify(mockConn,times(1)).prepareStatement(anyString(),new String []{"ID"});
        verify(mockPreparedStmnt, times(0)).setString(anyInt(), anyString());
        verify(mockPreparedStmnt, times(0)).execute();
        verify(mockConn, times(0)).commit();
        verify(mockResultSet, times(0)).next();
    }
}
