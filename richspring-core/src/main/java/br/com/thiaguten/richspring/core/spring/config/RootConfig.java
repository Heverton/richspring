package br.com.thiaguten.richspring.core.spring.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan(
        basePackages = {"br.com.thiaguten.richspring"},
        excludeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = Configuration.class)}
)
@Import(value = {PersistenceConfig.class})
public class RootConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:/i18n/messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

}
