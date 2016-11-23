package com.epam.ilya.action;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

/**
 * Class encapsulates methods for changing application's locale
 *
 * @author Ilya_Bondarenko
 */
public class ChangeLocaleAction extends DispatchAction {

    private static final String SUCCESS = "success";

    /**
     * Method changes on english locale
     *
     * @param mapping  of struts
     * @param form     came from view
     * @param request  going on view
     * @param response going on view
     * @return ActionForward object that contain mapping on forward page
     */
    public ActionForward english(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(
                Globals.LOCALE_KEY, Locale.ENGLISH);
        Config.set(request.getSession(), Config.FMT_LOCALE, Locale.ENGLISH);
        return mapping.findForward(SUCCESS);
    }

    /**
     * Method changes on russian locale
     *
     * @param mapping  of struts
     * @param form     came from view
     * @param request  going on view
     * @param response going on view
     * @return ActionForward object that contain mapping on forward page
     */
    public ActionForward russian(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(
                Globals.LOCALE_KEY, Locale.forLanguageTag("ru"));
        Config.set(request.getSession(), Config.FMT_LOCALE, Locale.forLanguageTag("ru"));
        return mapping.findForward(SUCCESS);
    }

    /**
     * Method changes on france locale
     *
     * @param mapping         of struts
     * @param ignoredForm     came from view
     * @param request         going on view
     * @param ignoredResponse going on view
     * @return ActionForward object that contain mapping on forward page
     */
    public ActionForward france(ActionMapping mapping, ActionForm ignoredForm, HttpServletRequest request, HttpServletResponse ignoredResponse) {
        request.getSession().setAttribute(
                Globals.LOCALE_KEY, Locale.FRANCE
        );
        Config.set(request.getSession(), Config.FMT_LOCALE, Locale.FRANCE);
        return mapping.findForward(SUCCESS);
    }
}
