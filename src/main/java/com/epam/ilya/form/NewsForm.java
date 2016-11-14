package com.epam.ilya.form;

import com.epam.ilya.model.Comment;
import com.epam.ilya.model.News;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;

/**
 * Data transfer object's class for containing news information
 *
 * @author Ilya_Bondarenko
 */

public class NewsForm extends ValidatorForm {
    private News news = new News();
    private Comment newComment = new Comment();
    private String editDate;

    public void setNews(News news) {
        this.news = news;
    }

    public News getNews() {
        return news;
    }

    public Comment getNewComment() {
        return newComment;
    }

    public void setNewComment(Comment newComment) {
        this.newComment = newComment;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }


}
