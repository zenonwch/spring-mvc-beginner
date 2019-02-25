package my.spring.project.springmvc.service.impl;

import my.spring.project.springmvc.domain.Cart;
import my.spring.project.springmvc.domain.CartItem;
import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.domain.repository.CartRepository;
import my.spring.project.springmvc.service.CartService;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final ProductService productService;
    private final CartRepository repository;

    public CartServiceImpl(final ProductService productService, final CartRepository repository) {
        this.productService = productService;
        this.repository = repository;
    }

    @Override
    public void create(final Cart cart) {
        validateCartItems(cart.getCartItems());

        repository.create(cart);
    }

    @Override
    public Cart read(final String cartId) {
        return repository.read(cartId);
    }

    @Override
    public void update(final Cart cart) {
        validateCartItems(cart.getCartItems());

        repository.update(cart);
    }

    @Override
    public void delete(final String cartId) {
        repository.delete(cartId);
    }

    @Override
    public void addItem(final String cartId, final String productId) {
        final Product product = productService.getProductById(productId);
        final Cart cart = repository.read(cartId);

        if (cart == null) {
            final Cart newCart = createNewCart(cartId, product);
            repository.create(newCart);
        } else {
            addNewItemToCart(cart, product);
            repository.update(cart);
        }
    }

    @Override
    public void removeItem(final String cartId, final String productId) {
        repository.removeItem(cartId, productId);
    }

    private void validateCartItems(final List<CartItem> cartItems) {
        for (final CartItem cartItem : cartItems) {
            final Product product = productService.getProductById(cartItem.getProductId());
            cartItem.setProduct(product);
        }
    }

    private Cart createNewCart(final String cartId, final Product product) {
        final Cart cart = new Cart(cartId);
        addNewItemToCart(cart, product);

        return cart;
    }

    private void addNewItemToCart(final Cart cart, final Product product) {
        final CartItem cartItem = new CartItem(cart.getId() + product.getProductId());
        cartItem.setProductId(product.getProductId());
        cartItem.setProduct(product);
        cartItem.setQuantity(1);

        cart.addCartItem(cartItem);
    }
}