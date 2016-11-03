package com.epam.ilya.action;

import com.epam.ilya.form.NewsForm;
import com.epam.ilya.model.News;
import com.epam.ilya.service.NewsService;
import com.epam.ilya.service.ServiceException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditNewsAction extends DispatchAction {
    private static final Logger LOG = LoggerFactory.getLogger(EditNewsAction.class);


    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("showNews"));
        NewsForm newsForm = (NewsForm) form;
        NewsService service = new NewsService();
        News news = newsForm.getNews();
        LOG.debug("Update news - {}",news);
        try {
            news = service.updateOrCreateNews(news);
        }catch (ServiceException e){
            throw new ActionException("Cannot update news",e);
        }
        actionRedirect.addParameter("id",news.getId());
        return actionRedirect;
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
}
