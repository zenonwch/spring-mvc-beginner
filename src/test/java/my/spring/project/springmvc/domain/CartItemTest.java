package my.spring.project.springmvc.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem("1");
    }

    @Test
    void cartItemTotalPriceShouldBeEqualToProductUnitPriceInCaseOfSingleQuantity() {
        final Product iphone = new Product("P1234", "iPhone XS", new BigDecimal(1500));
        cartItem.setProduct(iphone);

        final BigDecimal totalPrice = cartItem.getTotalPrice();

        assertEquals(iphone.getUnitPrice(), totalPrice);
    }

    @Test
    void cartItemTotalPriceInCaseMultipleQuantity() {
        final Product iphone = new Product("P1234", "iPhone XS", new BigDecimal(1500));
        cartItem.setProduct(iphone);
        cartItem.setQuantity(4);

        final BigDecimal totalPrice = cartItem.getTotalPrice();

        assertEquals(iphone.getUnitPrice().multiply(new BigDecimal(4)), totalPrice);
    }
}