package com.epam.ilya.form;

import com.epam.ilya.model.News;
import org.apache.struts.action.ActionForm;

import java.util.List;

public class NewsListForm extends ActionForm {
    private List<News> newses;

    public List<News> getNewses() {
        return newses;
    }

    public void setNewses(List<News> newses) {
        this.newses = newses;
    }
}
