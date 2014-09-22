package com.smart421;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart421.vendingmachine.exception.WebServiceHandlerExceptionResolver;

@Configuration
@ComponentScan({"com.smart421"})
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {


    @Bean
    public MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper());
        ArrayList<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(list);
        return converter;
    }

    /**
    * Provides the Jackson ObjectMapper with custom configuration for our JSON serialization.
    * @return The Jackson object mapper with non-null serialization configured
    */
    @Bean
    public ObjectMapper mapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    @Bean
    public WebServiceHandlerExceptionResolver webServiceHandlerExceptionResolver(){
        WebServiceHandlerExceptionResolver exceptionHandler = new WebServiceHandlerExceptionResolver();
        exceptionHandler.setJsonObjectMapper(mapper());
        return exceptionHandler;
    }

}
