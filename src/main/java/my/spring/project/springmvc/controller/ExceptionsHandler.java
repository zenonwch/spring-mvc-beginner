package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ModelAndView handleError(final HttpServletRequest request, final ProductNotFoundException ex) {
        final ModelAndView mav = new ModelAndView();
        final String msg = ex.getMessage();
        mav.addObject("message", msg);
        mav.addObject("exception", ex);
        final String url = request.getRequestURL() + "?" + request.getQueryString();
        mav.addObject("url", url);
        mav.setViewName("productNotFound");

        return mav;
    }
}