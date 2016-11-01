package com.epam.ilya.action;

import com.epam.ilya.form.NewsForm;
import com.epam.ilya.model.News;
import com.epam.ilya.service.NewsService;
import com.epam.ilya.service.ServiceException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowNewsViewPageAction extends Action {
    private static final String ID = "id";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter(ID);
        NewsService service = new NewsService();
        News news;
        try {
            news = service.getNewsById(id);
        } catch (ServiceException e) {
            throw new ActionException("Cannot get news by id",e);
        }
        NewsForm newsForm = (NewsForm) form;
        newsForm.setNews(news);
        return mapping.findForward("success");
    }
}
