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
import org.apache.struts.actions.MappingDispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class encapsulates all methods of manipulation with {@link News}
 *
 * @author Ilya_Bondarenko
 */

public class EditNewsAction extends MappingDispatchAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditNewsAction.class);
    private static final String ID = "id";

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
        String id = request.getParameter(ID);
        NewsService service = new NewsService();
        News news = newsForm.getNews();
        String date = newsForm.getEditDate();
        try {
            news = service.updateOrCreateNewsById(news, id, date);
        } catch (ServiceException e) {
            throw new ActionException("Cannot update news", e);
        }
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("showNews"));
        actionRedirect.addParameter(ID, news.getId());
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
        String id = request.getParameter(ID);
        LOGGER.debug("Delete news with id - {}", id);
        try {
            service.deleteNews(id);
        } catch (ServiceException e) {
            throw new ActionException("Cannot deactivate news", e);
        }
        return mapping.findForward("showNewsList");
    }
}
