package my.spring.project.springmvc.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class HomeController {

    private final MessageSource msgSource;

    public HomeController(@Qualifier("messageSource") final MessageSource msgSource) {
        this.msgSource = msgSource;
    }

    @GetMapping
    public String welcome(final Model model, final Locale locale) {
        final String greeting = msgSource.getMessage("welcome.page.greeting", null, locale);
        model.addAttribute("greeting", greeting);
        final String tagline = msgSource.getMessage("welcome.page.tagline", null, locale);
        model.addAttribute("tagline", tagline);

        return "welcome";
    }
}