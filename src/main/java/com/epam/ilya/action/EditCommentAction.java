package com.epam.ilya.action;

import com.epam.ilya.form.CommentForm;
import com.epam.ilya.form.NewsForm;
import com.epam.ilya.model.Comment;
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
 * Class encapsulates all methods of manipulation with {@link Comment}
 *
 * @author Ilya_Bondarenko
 */

public class EditCommentAction extends MappingDispatchAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCommentAction.class);
    private static final String ID = "id";
    private static final String NEWS_ID = "news_id";
    private static final String SUCCESS = "success";

    /**
     * Method creates new {@link Comment}
     *
     * @param mapping  of struts
     * @param form     contains new {@link Comment}
     * @param request  going on view
     * @param response going on view
     * @return ActionForward object that contain mapping on forward page
     */

    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ActionException {
        NewsService service = new NewsService();
        CommentForm commentForm = (CommentForm) form;
        String newsId = request.getParameter(NEWS_ID);
        Comment newComment = commentForm.getNewComment();
        LOGGER.debug("Try to create comment - {}", newComment);
        try {
            service.createCommentForNewsWithId(newComment, newsId);
        } catch (ServiceException e) {
            throw new ActionException("Cannot create comment for news", e);
        }
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(SUCCESS));
        actionRedirect.addParameter(ID, newsId);
        return actionRedirect;
    }

    /**
     * Method deletes selected {@link Comment}
     *
     * @param mapping  of struts
     * @param form     came from view
     * @param request  contains Comments and News id
     * @param response going on view
     * @return ActionForward object that contain mapping on forward page
     */

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ActionException {
        NewsService service = new NewsService();
        String id = request.getParameter(ID);
        String newsId = request.getParameter(NEWS_ID);
        LOGGER.debug("Delete comment with id -{} from news - {}", id, newsId);
        try {
            service.deleteComment(id);
        } catch (ServiceException e) {
            throw new ActionException("Cannot create comment for news", e);
        }
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(SUCCESS));
        actionRedirect.addParameter(ID, newsId);
        return actionRedirect;
    }
}
