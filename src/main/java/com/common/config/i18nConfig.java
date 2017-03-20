package com.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.Locale;

@Configuration
public class i18nConfig {

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        ArrayList<String> basenameList = new ArrayList<>();
        basenameList.add("messages");
        basenameList.add("messages_fr");

        String[] basenames = new String[basenameList.size()];
        basenames = basenameList.toArray(basenames);
        messageSource.setBasenames(basenames);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
