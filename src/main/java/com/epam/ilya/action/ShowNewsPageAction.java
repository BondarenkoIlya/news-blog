package com.epam.ilya.action;

import com.epam.ilya.form.NewsForm;
import com.epam.ilya.model.News;
import com.epam.ilya.service.NewsService;
import com.epam.ilya.service.ServiceException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowNewsPageAction extends DispatchAction {
    private static final String ID = "id";

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        takeNews((NewsForm) form, request);
        return mapping.findForward("view");
    }

    public ActionForward edition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        takeNews((NewsForm) form, request);
        return mapping.findForward("edition");
    }

    private void takeNews(NewsForm form, HttpServletRequest request) throws ActionException {
        String id = request.getParameter(ID);
        NewsService service = new NewsService();
        News news;
        try {
            news = service.getNewsById(id);
        } catch (ServiceException e) {
            throw new ActionException("Cannot get news by id",e);
        }
        NewsForm newsForm = form;
        newsForm.setNews(news);
    }
}
