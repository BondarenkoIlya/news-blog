package com.epam.ilya.action;

import com.epam.ilya.form.NewsForm;
import com.epam.ilya.model.News;
import com.epam.ilya.service.NewsService;
import com.epam.ilya.service.ServiceException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditNewsAction extends DispatchAction {
    private static final Logger LOG = LoggerFactory.getLogger(EditNewsAction.class);


    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NewsForm newsForm = (NewsForm) form;
        NewsService service = new NewsService();
        News news = newsForm.getNews();
        LOG.debug("Update news - {}",news);
        try {
            service.updateNews(news);
        }catch (ServiceException e){
            throw new ActionException("Cannot update news",e);
        }
        return mapping.findForward("success");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NewsService service = new NewsService();
        String id = request.getParameter("id");
        LOG.debug("Delete news with id - {}",id);
        try {
            service.deleteNews(id);
        } catch (ServiceException e) {
            throw new ActionException("Cannot deactivate news", e);
        }
        return mapping.findForward("showNewsList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NewsForm newsForm = (NewsForm) form;
        NewsService service = new NewsService();
        News news = newsForm.getNews();
        LOG.debug("Create news - {}",news);
        try {
            service.createNews(news);
        }catch (ServiceException e){
            throw new ActionException("Cannot create news",e);
        }
        return mapping.findForward("success");
    }
}
