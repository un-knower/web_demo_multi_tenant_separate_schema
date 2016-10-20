/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.context.MessageSource
 *  org.springframework.context.MessageSourceAware
 *  org.springframework.context.NoSuchMessageException
 *  org.springframework.context.i18n.LocaleContextHolder
 *  org.springframework.stereotype.Component
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Localizer implements MessageSourceAware {

    private static final Logger logger = LoggerFactory.getLogger(Localizer.class);
    private static MessageSource source;

    public void setMessageSource(MessageSource messageSource) {
        source = messageSource;
    }

    public static String getMessage(String key) {
        try {
            return Localizer.getMessage(key, Localizer.getDefaultLocale());
        } catch (NoSuchMessageException e) {
            logger.error("Message can be found!" + e.getMessage());
            return "";
        }
    }

    public static String getMessage(String key, Locale locale) {
        try {
            return source.getMessage(key, null, locale);
        } catch (NoSuchMessageException e) {
            logger.error("Message can be found!" + e.getMessage());
            return "";
        }
    }

    public static String getMessage(String key, Object ... args) {
        try {
            return Localizer.getMessage(key, args, Localizer.getDefaultLocale());
        } catch (NoSuchMessageException e) {
            logger.error("Message can be found!" + e.getMessage());
            return "";
        }
    }

    public static String getMessage(String key, Object[] args, Locale locale) {
        try {
            return source.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            logger.error("Message can be found!" + e.getMessage());
            return "";
        }
    }

    public static Locale getDefaultLocale() {
        String lang;
        switch (lang = AppConfig.getItem("lang", "")) {
            case "zh": {
                return new Locale("zh", "CN");
            }
            case "en": {
                return new Locale("en", "US");
            }
        }
        return LocaleContextHolder.getLocale();
    }
}

