package com.epam.ilya.action;

import com.epam.ilya.form.NewsListForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ShowNewsListPageAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NewsListForm newsListForm = (NewsListForm) form;
        newsListForm.setNewses(new ArrayList<>());
        return mapping.findForward("success");
    }
}
