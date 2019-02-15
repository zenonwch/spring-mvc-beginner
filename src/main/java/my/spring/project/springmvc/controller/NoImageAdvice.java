package my.spring.project.springmvc.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import my.spring.project.springmvc.config.MappingJackson2JsonViewExt;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class NoImageAdvice {
    private static final String ATTRIBUTE_NAME = "noImage";

    static {
        MappingJackson2JsonViewExt.excludeModelKey(ATTRIBUTE_NAME);
    }

    @JsonIgnore
    @ModelAttribute(ATTRIBUTE_NAME)
    public String getNoImage() {
        return "NoImage.png";
    }
}