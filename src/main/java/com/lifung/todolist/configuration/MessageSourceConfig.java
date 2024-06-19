package com.lifung.todolist.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {
    private static final String LOCALE_LANGUAGE = "en";
    private static final String LOCALE_COUNTRY = "GB";

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.addBasenames("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        Locale locale = Locale.of(LOCALE_LANGUAGE, LOCALE_COUNTRY);
        messageSource.setDefaultLocale(locale);
        return messageSource;
    }
}
