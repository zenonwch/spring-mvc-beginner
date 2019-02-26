package my.spring.project.springmvc.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Cart implements Serializable {
    private static final long serialVersionUID = 0L;

    @Setter(AccessLevel.NONE)
    private final String id;

    private List<CartItem> cartItems;
    private BigDecimal grandTotal;

    public Cart(final String id) {
        this.id = id;
        cartItems = new ArrayList<>();
    }

    public BigDecimal getGrandTotal() {
        updateGrandTotal();
        return grandTotal;
    }

    public void addCartItem(final CartItem cartItem) {
        final CartItem existedCartItem = cartItems.stream()
                .filter(ci -> Objects.equals(cartItem.getProductId(), ci.getProductId()))
                .findAny().orElse(null);

        if (existedCartItem != null) {
            existedCartItem.setQuantity(existedCartItem.getQuantity() + 1);
        } else {
            cartItems.add(cartItem);
        }
    }

    private void updateGrandTotal() {
        final BigDecimal grandTotal = cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        setGrandTotal(grandTotal);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Cart)) return false;

        final Cart cart = (Cart) o;
        return id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}