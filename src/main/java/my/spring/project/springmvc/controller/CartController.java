package my.spring.project.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String get(final HttpServletRequest request) {
        return "redirect:/cart/" + request.getSession(true).getId();
    }

    @GetMapping("/{cartId}")
    public String getCart(@PathVariable final String cartId, final Model model) {
        model.addAttribute("cartId", cartId);

        return "cart";
    }
}