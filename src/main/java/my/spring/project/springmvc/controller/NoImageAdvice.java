package my.spring.project.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class NoImageAdvice {

    @ModelAttribute("noImage")
    public String getNoImage() {
        return "NoImage.png";
    }
}