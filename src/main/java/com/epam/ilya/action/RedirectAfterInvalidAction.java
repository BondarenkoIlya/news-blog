package com.epam.ilya.action;

import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectAfterInvalidAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String referer = request.getHeader("referer");
        if (referer.endsWith("newsNew.do")) {
            return mapping.findForward("newsNew");
        }
        String next = (String) request.getSession().getAttribute("next");
        int id = (int) request.getSession().getAttribute("newsId");
        if (next.equals("addComment")) {
            ActionRedirect addComment = new ActionRedirect(mapping.findForward("addComment"));
            addComment.addParameter("id", id);
            return addComment;
        }
        if (next.equals("newsEdition")) {
            ActionRedirect newsEdition = new ActionRedirect(mapping.findForward("newsEdition"));
            newsEdition.addParameter("id", id);
            return newsEdition;
        }
        return mapping.findForward("newsEdition");//return 404
    }
}
