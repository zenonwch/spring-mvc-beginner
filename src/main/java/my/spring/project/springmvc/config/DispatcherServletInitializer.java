package my.spring.project.springmvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Arrays;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootApplicationContextConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebApplicationContextConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {

        // https://wiki.apache.org/tomcat/FAQ/CharacterEncoding
        servletContext.setRequestCharacterEncoding("UTF-8");
        servletContext.setResponseCharacterEncoding("UTF-8");

        super.onStartup(servletContext);
    }

    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        final Filter[] servletFilters = super.getServletFilters();

        if (servletFilters == null) {
            return new Filter[]{characterEncodingFilter};
        }

        final ArrayList<Filter> filters = new ArrayList<>(Arrays.asList(servletFilters));
        filters.add(characterEncodingFilter);

        return filters.toArray(servletFilters);
    }
}