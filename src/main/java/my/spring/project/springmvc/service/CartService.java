package my.spring.project.springmvc.service;

import my.spring.project.springmvc.domain.Cart;

public interface CartService {
    void create(Cart cart);

    Cart read(String cartId);

    void update(Cart cart);

    void delete(String cartId);

    void addItem(String cartId, String productId);

    void removeItem(String cartId, String productId);
}