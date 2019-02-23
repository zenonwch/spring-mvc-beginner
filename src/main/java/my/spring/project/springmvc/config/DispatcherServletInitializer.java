package my.spring.project.springmvc.config;

import my.spring.project.springmvc.config.jsp.JspConfigDescriptorImpl;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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

        TomcatStandardContextReflectionSetter.setJspConfigDescriptor(servletContext, new JspConfigDescriptorImpl());

        super.onStartup(servletContext);
    }
}