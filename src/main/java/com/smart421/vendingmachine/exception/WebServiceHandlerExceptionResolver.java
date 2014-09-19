package com.smart421.vendingmachine.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;





public class WebServiceHandlerExceptionResolver extends AbstractHandlerExceptionResolver implements InitializingBean {

    @SuppressWarnings("unused")
    private ObjectMapper jsonObjectMapper;


    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                    Exception ex) {

        if (ex instanceof HttpMessageNotReadableException) {
            Throwable cause = ex.getCause();
            if (cause instanceof InvalidFormatException) {

            } else {
                //400 (Bad Request) - request is not valid

            }
        }

            return null;
    }


    /**
     * @param jsonObjectMapper the jsonObjectMapper to set
     */
    public void setJsonObjectMapper(ObjectMapper objectMapper) {
        this.jsonObjectMapper = objectMapper;
    }


}