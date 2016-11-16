package com.epam.ilya.form;

import com.epam.ilya.model.Comment;
import com.epam.ilya.model.News;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

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

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        String referer = request.getHeader("referer");

        if (referer.endsWith("newsEdition.do?method=view&id=" + news.getId())){
            request.getSession().setAttribute("next","addComment");
            request.getSession().setAttribute("newsId",news.getId());
            validateNewComment(errors);
        }
        if (referer.endsWith("newsEdition.do?method=edition&id=" + news.getId())){
            request.getSession().setAttribute("next","newsEdition");
            request.getSession().setAttribute("newsId",news.getId());
            validateNews(errors);
        }
        if (referer.endsWith("newsNew.do")){
            validateNews(errors);
        }
        return errors;
    }

    public void resetNewComment() {
        newComment.setAuthor("");
        newComment.setContent("");
    }

    private void validateNewComment(ActionErrors errors) {
        if (!newComment.getAuthor().matches(".{1,50}")){
            errors.add("author",new ActionMessage("err.comment.author.required"));
        }
        if (!newComment.getContent().matches(".{1,400}")){
            errors.add("content",new ActionMessage("err.comment.content.required"));
        }
    }

    private void validateNews(ActionErrors errors) {
        if (!news.getTitle().matches(".{1,400}")){
            errors.add("title",new ActionMessage("err.news.title.required"));
        }
        if (!news.getBrief().matches(".{1,4000}")){
            errors.add("brief",new ActionMessage("err.news.brief.required"));
        }
        if (!news.getContent().matches(".{1,4000}")){
            errors.add("content",new ActionMessage("err.news.content.required"));
        }
        DateTime dateTime;
        try {
            DateTimeFormatter pattern = DateTimeFormat.forPattern("dd/MM/yyyy");
            dateTime = pattern.parseDateTime(editDate);
            news.setDate(dateTime);
            if (dateTime.isAfterNow()){
                errors.add("dataAfterNow",new ActionMessage("err.news.date.after"));
            }
        } catch (IllegalArgumentException e) {
            errors.add("dataAfterNow",new ActionMessage("err.news.date.unparseable"));
        }
    }
}
