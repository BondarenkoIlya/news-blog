package com.epam.ilya.action;

import com.epam.ilya.form.NewsListForm;
import com.epam.ilya.model.News;
import com.epam.ilya.service.NewsService;
import com.epam.ilya.service.ServiceException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowNewsListPageAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NewsListForm newsListForm = (NewsListForm) form;
        NewsService service = new NewsService();
        List<News> newsList;
        try {
            newsList = service.getNewsList();
        } catch (ServiceException e) {
            throw new ActionException("Service cannot get news list",e);
        }
        newsListForm.setNewsList(newsList);
        return mapping.findForward("success");
    }
}
