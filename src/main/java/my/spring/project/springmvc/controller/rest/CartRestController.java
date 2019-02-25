package my.spring.project.springmvc.controller.rest;

import my.spring.project.springmvc.controller.mapper.CartMapper;
import my.spring.project.springmvc.domain.Cart;
import my.spring.project.springmvc.dto.CartDto;
import my.spring.project.springmvc.exception.CartNotFoundException;
import my.spring.project.springmvc.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@RequestMapping("rest/cart")
public class CartRestController {

    private final CartService service;

    public CartRestController(final CartService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final CartDto cartDto) {
        final Cart cart = CartMapper.cartFromDto(cartDto);
        service.create(cart);
    }

    @GetMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto read(@PathVariable final String cartId) {
        final Cart cart = service.read(cartId);
        return CartMapper.cartToDto(cart);
    }

    @PutMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable final String cartId,
                       @RequestBody final CartDto cartDto) {
        if (!Objects.equals(cartId, cartDto.getId()) || service.read(cartId) == null) {
            throw new CartNotFoundException("Not found cart with id " + cartId);
        }

        final Cart cart = CartMapper.cartFromDto(cartDto);
        service.update(cart);
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable final String cartId) {
        service.delete(cartId);
    }

    @PutMapping("/add/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void addItem(@PathVariable final String productId, final HttpSession session) {
        final String cartId = session.getId();
        service.addItem(cartId, productId);
    }

    @PutMapping("/remove/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeItem(@PathVariable final String productId, final HttpSession session) {
        final String cartId = session.getId();
        service.removeItem(cartId, productId);
    }
}