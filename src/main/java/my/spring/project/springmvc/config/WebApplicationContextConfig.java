package my.spring.project.springmvc.config;

import com.thoughtworks.xstream.XStream;
import my.spring.project.springmvc.domain.Customer;
import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.interceptor.ProcessingTimeLogInterceptor;
import my.spring.project.springmvc.interceptor.PromoCodeInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan("my.spring.project.springmvc")
public class WebApplicationContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // Overridden to support @MatrixVariable in controllers
    @Override
    public void configurePathMatch(final PathMatchConfigurer configurer) {
        final UrlPathHelper helper = new UrlPathHelper();
        helper.setRemoveSemicolonContent(false);

        configurer.setUrlPathHelper(helper);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/pdf/**").addResourceLocations("/resources/pdf/");
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new ProcessingTimeLogInterceptor());

        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);

        final HandlerInterceptor promoCodeInterceptor = promoCodeInterceptor();
        registry.addInterceptor(promoCodeInterceptor).addPathPatterns("/**/market/products/specialOffer");
    }

    @Override
    public Validator getValidator() {
        return validatorBean();
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setCache(false);

        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        final CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSizePerFile(10_485_760); // 10 MB

        return multipartResolver;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        resolver.setCookieName("locale");

        return resolver;
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(final ContentNegotiationManager manager) {
        final ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(manager);

        final MappingJackson2JsonView jsonView = jsonView();
        final MarshallingView xmlView = xmlView();
        viewResolver.setDefaultViews(Arrays.asList(jsonView, xmlView));

        return viewResolver;
    }

    @Bean
    public MappingJackson2JsonView jsonView() {
        final MappingJackson2JsonViewExt jsonView = new MappingJackson2JsonViewExt();
        jsonView.setPrettyPrint(true);

        return jsonView;
    }

    @Bean
    public MarshallingView xmlView() {
        final XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setSupportedClasses(Product.class, Customer.class, Collection.class);

        final XStream xStream = marshaller.getXStream();
        xStream.alias("product", Product.class);
        xStream.alias("customer", Customer.class);

        return new MarshallingView(marshaller);
    }

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages", "validationMessages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public HandlerInterceptor promoCodeInterceptor() {
        final PromoCodeInterceptor interceptor = new PromoCodeInterceptor();
        interceptor.setPromoCode("OFF3R");
        interceptor.setOfferRedirect("market/products");
        interceptor.setErrorRedirect("invalidPromoCode");

        return interceptor;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validatorBean() {
        final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        final MessageSource messageSource = messageSource();
        validator.setValidationMessageSource(messageSource);

        return validator;
    }
}