package com.epam.ilya.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RedirectAfterInvalidAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String id = (String) req.getSession().getAttribute("id");
        String ref = (String) req.getSession().getAttribute("ref");
        ref = "";
        return mapping.findForward("newsEdition");
    }
}
