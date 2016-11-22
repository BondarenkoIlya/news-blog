package com.epam.ilya.action;

import com.epam.ilya.form.CommentForm;
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

/**
 * Class encapsulates functional for showing pages that contain {@link News}
 *
 * @author Ilya_Bondarenko
 */

public class ShowNewsPageAction extends DispatchAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowNewsPageAction.class);
    private static final String ID = "id";

    /**
     * Method takes selected {@link News} and sends it to page for displaying
     *
     * @param mapping  of struts
     * @param form     going to page for displaying
     * @param request  going on view
     * @param response going on view
     * @return ActionForward object that contain mapping on forward page
     */

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ActionException {
        takeNews((NewsForm) form, request, response);
        request.setAttribute("commentForm", new CommentForm());
        return mapping.findForward("view");
    }

    /**
     * Method takes selected {@link News} and sends it to page for edition
     *
     * @param mapping  of struts
     * @param form     going to page for displaying and edition
     * @param request  going on view
     * @param response going on view
     * @return ActionForward object that contain mapping on forward page
     */

    public ActionForward edition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ActionException {
        takeNews((NewsForm) form, request, response);
        return mapping.findForward("edition");
    }

    private void takeNews(NewsForm form, HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String id = request.getParameter(ID);
        NewsService service = new NewsService();
        News news = new News();
        try {
            news = service.getNewsById(id);
        } catch (ServiceException e) {
            LOGGER.error("Cannot get news by id", e);
            throw new ActionException("Cannot get news", e);
        }

        NewsForm newsForm = form;
        newsForm.setEditDate(news.getDate().toString("dd/MM/yyyy"));
        newsForm.setNews(news);
    }
}
