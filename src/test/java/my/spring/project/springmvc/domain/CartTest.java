package my.spring.project.springmvc.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart("0001");
    }

    @Test
    void cartGrandTotalPriceForOneCartItemWithSingleProduct() {
        final Product product = new Product("P001", "Test Product 1", new BigDecimal(1000));
        final CartItem cartItem = new CartItem("CI001");
        cartItem.setProduct(product);
        cart.setCartItems(Collections.singletonList(cartItem));

        final BigDecimal grandTotal = cart.getGrandTotal();

        assertEquals(product.getUnitPrice(), grandTotal);
    }

    @Test
    void cartGrandTotalForMultipleCartItemsWithTheSameProduct() {
        final Product product = new Product("P001", "Test Product 1", new BigDecimal(1000));
        final CartItem cartItem1 = new CartItem("CI001");
        cartItem1.setProduct(product);
        final CartItem cartItem2 = new CartItem("CI002");
        cartItem2.setProduct(product);

        cart.addCartItem(cartItem1);
        cart.addCartItem(cartItem2);

        assertEquals(1, cart.getCartItems().size());
        final CartItem cartItem = cart.getCartItems().get(0);
        assertEquals(2, cartItem.getQuantity());
        assertEquals(product, cartItem.getProduct());
        assertEquals(product.getUnitPrice().multiply(new BigDecimal(2)), cart.getGrandTotal());
    }

    @Test
    void cartGrandTotalForMultipleCartItemsWithDifferentProducts() {
        final Product product1 = new Product("P001", "Test Product 1", new BigDecimal(100));
        final Product product2 = new Product("P002", "Test Product 2", new BigDecimal(200));
        final CartItem cartItem1 = new CartItem("CI001");
        cartItem1.setProduct(product1);
        cart.addCartItem(cartItem1);
        final CartItem cartItem2 = new CartItem("CI002");
        cartItem2.setProduct(product2);
        cart.addCartItem(cartItem2);
        final CartItem cartItem3 = new CartItem("CI003");
        cartItem3.setProduct(product2);
        cart.addCartItem(cartItem3);

        assertEquals(2, cart.getCartItems().size());
        assertEquals(product1, cart.getCartItems().get(0).getProduct());
        assertEquals(1, cart.getCartItems().get(0).getQuantity());
        assertEquals(product2, cart.getCartItems().get(1).getProduct());
        assertEquals(2, cart.getCartItems().get(1).getQuantity());
        assertEquals(new BigDecimal(500), cart.getGrandTotal());
    }
}