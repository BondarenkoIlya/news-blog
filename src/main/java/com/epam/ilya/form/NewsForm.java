package com.epam.ilya.form;

import com.epam.ilya.model.News;
import org.apache.struts.action.ActionForm;

public class NewsForm extends ActionForm {
    News news;

    public void setNews(News news) {
        this.news = news;
    }

    public News getNews() {
        return news;
    }
}
