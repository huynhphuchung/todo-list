package com.lifung.todolist.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageResource {

    private final MessageSource messageSource;

    public String getMessage(String key, String... params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }

    public String getMessage(String key, Locale local, String... params) {
        return messageSource.getMessage(key, params, local);
    }
}
