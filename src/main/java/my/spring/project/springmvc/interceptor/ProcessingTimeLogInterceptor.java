package my.spring.project.springmvc.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessingTimeLogInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LogManager.getLogger(ProcessingTimeLogInterceptor.class);
    private static final String START_REQ_TIME_ATTR_NAME = "startReqTime";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        final long startTime = System.currentTimeMillis();
        request.setAttribute(START_REQ_TIME_ATTR_NAME, startTime);

        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
                           final Object handler, final ModelAndView modelAndView) {
        final String queryString = request.getQueryString();
        final String path = request.getRequestURL() + (queryString == null ? "" : '?' + queryString);
        final String method = request.getMethod();

        final long startTime = (long) request.getAttribute(START_REQ_TIME_ATTR_NAME);
        final long endTime = System.currentTimeMillis();

        LOG.info("{} millisecond taken to process the {} request {}.", endTime - startTime, method, path);
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) {
        // NO operation
    }
}