package com.epam.ilya.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * Class-filter for work with view's character encoding.
 * Mapped on url - "/*"
 *
 * @author Ilya_Bondarenko
 */

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing...
    }

    /**
     * Method sets character encoding to all request.
     *
     * @param request  request that come from view
     * @param response response that go to view
     * @param chain     parameter for work with next filters
     * @throws IOException
     * @throws ServletException
     */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // do nothing...
    }
}
