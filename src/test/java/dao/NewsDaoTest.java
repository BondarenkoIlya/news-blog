package dao;

import com.epam.ilya.dao.NewsDao;
import com.epam.ilya.model.News;
import org.junit.Test;

import static org.junit.Assert.*;


public class NewsDaoTest {
    @Test
    public void create() throws Exception {
        News news = new News();
        news.setTitle("test");
        news.setBrief("test");
        news.setContent("test");
        NewsDao newsDao = new NewsDao();
        newsDao.create(news);
        assertTrue(news.getId()>0);
    }

}