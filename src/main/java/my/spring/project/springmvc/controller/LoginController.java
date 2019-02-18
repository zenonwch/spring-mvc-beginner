package my.spring.project.springmvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private static final String LOGOUT = "logout";
    private static final String ERROR = "error";
    private static final String ACCESS_DENIED = "accessDenied";

    @GetMapping("/login")
    public String login(final Authentication authentication, final Model model) {
        final boolean accessDenied = model.containsAttribute(ACCESS_DENIED);

        if (authentication != null && authentication.isAuthenticated() && !accessDenied) {
            return "/products";
        }

        return "login";
    }

    @GetMapping(value = "/login", params = ERROR)
    public String error(final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(ERROR, true);

        return "redirect:/login";
    }

    @GetMapping(value = "/login", params = ACCESS_DENIED)
    public String accessDenied(final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(ACCESS_DENIED, true);

        return "redirect:/login";
    }

    @GetMapping(value = "/login", params = LOGOUT)
    public String logout(final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(LOGOUT, true);

        return "redirect:/login";
    }
}