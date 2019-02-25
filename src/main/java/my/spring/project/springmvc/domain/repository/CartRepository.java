package my.spring.project.springmvc.domain.repository;

import my.spring.project.springmvc.domain.Cart;

public interface CartRepository {
    void create(Cart cart);

    Cart read(String id);

    void update(Cart cart);

    void delete(String id);

    void removeItem(String cartId, String productId);
}