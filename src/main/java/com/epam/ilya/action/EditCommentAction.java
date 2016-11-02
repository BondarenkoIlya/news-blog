package com.epam.ilya.action;

import com.epam.ilya.form.NewsForm;
import com.epam.ilya.model.Comment;
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

public class EditCommentAction extends DispatchAction {
    private static final Logger LOG = LoggerFactory.getLogger(EditCommentAction.class);


    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("success"));
        NewsService service = new NewsService();
        NewsForm newsForm = (NewsForm) form;
        News news = newsForm.getNews();
        Comment newComment = newsForm.getNewComment();
        try {
            newComment = service.createCommentForNews(newComment, news);
        }catch (ServiceException e){
            throw new ActionException("Cannot create comment for news",e);
        }
        newsForm.getNews().getComments().add(newComment);
        actionRedirect.addParameter("id", news.getId());
        return actionRedirect;
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("success"));
        NewsService service = new NewsService();
        NewsForm newsForm = (NewsForm) form;
        News news = newsForm.getNews();
        String id = request.getParameter("id");
        LOG.debug("Delete comment with id -{} from news - {}",id,news);
        try {
            service.deactivateNewsComment(id,news);
        }catch (ServiceException e){
            throw new ActionException("Cannot create comment for news",e);
        }
        actionRedirect.addParameter("id", news.getId());
        return actionRedirect;
    }
}
