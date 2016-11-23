package com.epam.ilya.form;

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
    private String editDate;

    public void setNews(News news) {
        this.news = news;
    }

    public News getNews() {
        return news;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = super.validate(mapping, request);
        validateNewsDate(errors);
        return errors;
    }

    private void validateNewsDate(ActionErrors errors) {
        DateTime dateTime;
        try {
            DateTimeFormatter pattern = DateTimeFormat.forPattern("dd/MM/yyyy");
            dateTime = pattern.parseDateTime(editDate);
            news.setDate(dateTime);
            if (dateTime.isAfterNow()) {
                errors.add("dataAfterNow", new ActionMessage("err.news.date.after"));
            }
        } catch (IllegalArgumentException e) {
            errors.add("dataAfterNow", new ActionMessage("err.news.date.unparseable"));
        }
    }
}
