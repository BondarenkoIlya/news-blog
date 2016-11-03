package com.epam.ilya.action;

import com.epam.ilya.form.NewsForm;
import com.epam.ilya.model.Comment;
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

public class EditCommentAction extends DispatchAction {
    private static final Logger LOG = LoggerFactory.getLogger(EditCommentAction.class);


    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("success"));
        NewsService service = new NewsService();
        NewsForm newsForm = (NewsForm) form;
        String newsId = request.getParameter("news_id");
        Comment newComment = newsForm.getNewComment();
        LOG.debug("Try to create comment - {}", newComment);
        try {
            service.createCommentForNewsWithId(newComment, newsId);
        } catch (ServiceException e) {
            throw new ActionException("Cannot create comment for news", e);
        }
        actionRedirect.addParameter("id", newsId);
        return actionRedirect;
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("success"));
        NewsService service = new NewsService();
        String id = request.getParameter("id");
        String newsId = request.getParameter("news_id");
        LOG.debug("Delete comment with id -{} from news - {}", id, newsId);
        try {
            service.deleteComment(id);
        } catch (ServiceException e) {
            throw new ActionException("Cannot create comment for news", e);
        }
        actionRedirect.addParameter("id", newsId);
        return actionRedirect;
    }
}
