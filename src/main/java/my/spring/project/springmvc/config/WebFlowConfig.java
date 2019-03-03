package my.spring.project.springmvc.config;

import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.datetime.standard.TemporalAccessorParser;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.util.StringUtils;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.time.temporal.ChronoField.*;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

    @Bean
    public FlowDefinitionRegistry flowRegistry() {
        final FlowBuilderServices flowBuilderServices = flowBuilderServices();

        return getFlowDefinitionRegistryBuilder()
                .setBasePath("/WEB-INF/flows")
                .addFlowLocationPattern("/**/*-flow.xml")
                .setFlowBuilderServices(flowBuilderServices)
                .build();
    }

    @Bean
    public FlowBuilderServices flowBuilderServices() {
        final FormattingConversionService conversionService = conversionServiceFactoryBean().getObject();

        return getFlowBuilderServicesBuilder()
                .setConversionService(new DefaultConversionService(Objects.requireNonNull(conversionService)))
                .build();
    }

    @Bean
    public FormattingConversionServiceFactoryBean conversionServiceFactoryBean() {
        final Set<? super AnnotationFormatterFactory<DateTimeFormat>> formatters = new HashSet<>(1);
        formatters.add(new EnhancedJsr310DateTimeFormatAnnotationFormatterFactory());

        final FormattingConversionServiceFactoryBean fcs = new FormattingConversionServiceFactoryBean();
        fcs.setFormatters(formatters);
        return fcs;
    }

    @Bean
    public FlowExecutor flowExecutor() {
        final FlowDefinitionRegistry flowRegistry = flowRegistry();
        return getFlowExecutorBuilder(flowRegistry).build();
    }

    @Bean
    public FlowHandlerMapping flowHandlerMapping() {
        final FlowHandlerMapping flowHandlerMapping = new FlowHandlerMapping();
        flowHandlerMapping.setOrder(-1);

        final FlowDefinitionRegistry flowRegistry = flowRegistry();
        flowHandlerMapping.setFlowRegistry(flowRegistry);

        return flowHandlerMapping;
    }

    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {
        final FlowHandlerAdapter adapter = new FlowHandlerAdapter();

        final FlowExecutor flowExecutor = flowExecutor();
        adapter.setFlowExecutor(flowExecutor);
        adapter.setSaveOutputToFlashScopeOnRedirect(true);

        return adapter;
    }

    private static class EnhancedJsr310DateTimeFormatAnnotationFormatterFactory extends Jsr310DateTimeFormatAnnotationFormatterFactory {
        @Override
        public Parser<?> getParser(final DateTimeFormat annotation, final Class<?> fieldType) {
            final String pattern = annotation.pattern();
            final String resolvedPattern = resolveEmbeddedValue(pattern);

            if (StringUtils.hasLength(resolvedPattern)) {
                final int currentYear = Year.now().getValue();

                final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                        .appendPattern(pattern)
                        .parseDefaulting(YEAR, currentYear)
                        .parseDefaulting(HOUR_OF_DAY, 0)
                        .parseDefaulting(MINUTE_OF_HOUR, 0)
                        .parseDefaulting(SECOND_OF_MINUTE, 0)
                        .toFormatter();

                //noinspection unchecked
                return new TemporalAccessorParser((Class<? extends TemporalAccessor>) fieldType, formatter);
            }

            return super.getParser(annotation, fieldType);
        }
    }
}