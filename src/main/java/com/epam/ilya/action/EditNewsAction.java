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

/**
 * Class encapsulates all methods of manipulation with {@link News}
 *
 * @author Ilya_Bondarenko
 */

public class EditNewsAction extends DispatchAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditNewsAction.class);

    /**
     * Method creates new or updates already exists {@link News}
     *
     * @param mapping  of struts
     * @param form     contains new {@link News}
     * @param request  contains id of {@link News}
     * @param response going on view
     * @return ActionForward object that contain mapping on forward page
     */

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ActionException {
        LOGGER.debug("Update or create news");
        NewsForm newsForm = (NewsForm) form;
        String id = request.getParameter("id");
        NewsService service = new NewsService();
        News news = newsForm.getNews();
        try {
            news = service.updateOrCreateNewsById(news,id);
        }catch (ServiceException e){
            throw new ActionException("Cannot update news",e);
        }
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("showNews"));
        actionRedirect.addParameter("id",news.getId());
        return actionRedirect;
    }

    /**
     * Method deletes selected {@link News}
     *
     * @param mapping  of struts
     * @param form     came from view
     * @param request  contains id of selected {@link News}
     * @param response going on view
     * @return ActionForward object that contain mapping on forward page
     */

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ActionException {
        NewsService service = new NewsService();
        String id = request.getParameter("id");
        LOGGER.debug("Delete news with id - {}",id);
        try {
            service.deleteNews(id);
        } catch (ServiceException e) {
            throw new ActionException("Cannot deactivate news", e);
        }
        return mapping.findForward("showNewsList");
    }
}
