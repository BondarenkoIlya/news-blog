package com.epam.ilya.form;

import com.epam.ilya.model.Comment;
import com.epam.ilya.model.News;
import org.apache.struts.action.ActionForm;

/**
 * Data transfer object's class for containing news information
 *
 * @author Ilya_Bondarenko
 */

public class NewsForm extends ActionForm {
    private News news = new News();
    private Comment newComment = new Comment();

    public void setNews(News news) {
        this.news = news;
    }

    public News getNews() {
        return news;
    }

    public Comment getNewComment() {
        return newComment;
    }
}
