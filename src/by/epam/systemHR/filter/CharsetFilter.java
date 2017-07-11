package by.epam.systemHR.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by irinaleibutina on 21.03.17.
 */

public class CharsetFilter implements Filter {

    private String encoding;
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("characterEncoding");
        context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            ((HttpServletRequest) request).setCharacterEncoding(encoding);
            ((HttpServletResponse) response).setCharacterEncoding(encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        context = null;
    }
}
