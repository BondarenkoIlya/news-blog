package com.epam.ilya.form;

import com.epam.ilya.model.News;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Data transfer object's class for containing news list's information
 *
 * @author Ilya_Bondarenko
 */

public class NewsListForm extends ActionForm {
    private List<News> newsList;

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
