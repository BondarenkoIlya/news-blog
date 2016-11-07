package com.epam.ilya.action;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class ChangeLocaleAction extends DispatchAction {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeLocaleAction.class);

    public ActionForward english(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute(
                Globals.LOCALE_KEY, Locale.ENGLISH);
        Config.set(request.getSession(), Config.FMT_LOCALE, Locale.ENGLISH);
        return mapping.findForward("success");
    }

    public ActionForward russian(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute(
                Globals.LOCALE_KEY, Locale.forLanguageTag("ru"));
        Config.set(request.getSession(), Config.FMT_LOCALE, Locale.forLanguageTag("ru"));
        return mapping.findForward("success");
    }

    public ActionForward france(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute(
                Globals.LOCALE_KEY, Locale.FRANCE);
        Config.set(request.getSession(), Config.FMT_LOCALE, Locale.FRANCE);
        return mapping.findForward("success");
    }
}
