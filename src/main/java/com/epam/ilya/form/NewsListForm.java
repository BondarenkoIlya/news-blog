package com.epam.ilya.form;

import com.epam.ilya.model.News;
import org.apache.struts.action.ActionForm;

import java.util.List;

public class NewsListForm extends ActionForm {
    private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
