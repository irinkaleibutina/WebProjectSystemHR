package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by irinaleibutina on 21.03.17.
 */
public class LocaleFilter implements Filter {

    private static final String ATTR_LOCALE = "locale";
    private static final String EN_LOCALE = "eng";
    private static final String RU_LOCALE = "ru";
    private String locale;

    public LocaleFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        locale = RU_LOCALE;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getSession().getAttribute(ATTR_LOCALE) == null) {
            request.getSession().setAttribute(ATTR_LOCALE, locale);
        }
        if (request.getSession().getAttribute(ATTR_LOCALE) == EN_LOCALE) {
            request.getSession().setAttribute(ATTR_LOCALE, EN_LOCALE);
        }
        if (request.getSession().getAttribute(ATTR_LOCALE) == RU_LOCALE) {
            request.getSession().setAttribute(ATTR_LOCALE, RU_LOCALE);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
