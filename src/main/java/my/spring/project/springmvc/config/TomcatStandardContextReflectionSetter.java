package my.spring.project.springmvc.config;

import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationContextFacade;
import org.apache.catalina.core.StandardContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.ReflectionException;
import javax.servlet.ServletContext;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.lang.reflect.Field;

final class TomcatStandardContextReflectionSetter {
    private static final Logger LOG = LogManager.getLogger(TomcatStandardContextReflectionSetter.class);

    private static final String CONTEXT_FIELD_NAME = "context";

    private TomcatStandardContextReflectionSetter() {
    }

    static void setJspConfigDescriptor(final ServletContext servletContext,
                                       final JspConfigDescriptor jspConfigDescriptor) {
        try {
            final ApplicationContextFacade appContextFacade = (ApplicationContextFacade) servletContext;

            final StandardContext context = getStandardContext(appContextFacade);
            context.setJspConfigDescriptor(jspConfigDescriptor);
        } catch (final ReflectionException ex) {
            LOG.warn("JspConfigDescriptor was not set due to org.apache.catalina.core.StandardContext extraction failure", ex);
        } catch (final ClassCastException ex) {
            LOG.warn("JspConfigDescriptor was not set. ServletContext is not an instance of org.apache.catalina.core.ApplicationContextFacade");
        }
    }

    private static StandardContext getStandardContext(final ApplicationContextFacade appContextFacade) throws ReflectionException {
        try {
            final ApplicationContext appContext = getApplicationContext(appContextFacade);

            final Field stContextField = appContext.getClass().getDeclaredField(CONTEXT_FIELD_NAME);
            stContextField.setAccessible(true);

            return (StandardContext) stContextField.get(appContext);
        } catch (final NoSuchFieldException ex) {
            LOG.warn("Field '{}' was not found in the org.apache.catalina.core.ApplicationContext.", CONTEXT_FIELD_NAME);
            throw new ReflectionException(ex);
        } catch (final IllegalAccessException ex) {
            LOG.warn("Field '{}' is inaccessible in the org.apache.catalina.core.ApplicationContext.", CONTEXT_FIELD_NAME);
            throw new ReflectionException(ex);
        } catch (final Exception ex) {
            LOG.warn("org.apache.catalina.core.StandardContext extraction failed.", ex);
            throw new ReflectionException(ex);
        }
    }

    private static ApplicationContext getApplicationContext(final ApplicationContextFacade appContextFacade) throws ReflectionException {
        try {
            final Field appContextField = appContextFacade.getClass().getDeclaredField(CONTEXT_FIELD_NAME);
            appContextField.setAccessible(true);

            return (ApplicationContext) appContextField.get(appContextFacade);
        } catch (final NoSuchFieldException ex) {
            LOG.warn("Field '{}' was not found in the org.apache.catalina.core.ApplicationContextFacade.", CONTEXT_FIELD_NAME);
            throw new ReflectionException(ex);
        } catch (final IllegalAccessException ex) {
            LOG.warn("Field '{}' is inaccessible in the org.apache.catalina.core.ApplicationContextFacade.", CONTEXT_FIELD_NAME);
            throw new ReflectionException(ex);
        } catch (final Exception ex) {
            LOG.warn("org.apache.catalina.core.ApplicationContext extraction failed.", ex);
            throw new ReflectionException(ex);
        }
    }
}