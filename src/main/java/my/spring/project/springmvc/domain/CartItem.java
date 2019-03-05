package my.spring.project.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class CartItem implements Serializable {
    private static final long serialVersionUID = 0L;

    private final String id;
    @Setter
    private String productId;
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;

    public CartItem(final String id) {
        this.id = id;
    }

    public void setProduct(final Product product) {
        this.product = product;
        productId = product.getProductId();

        if (quantity == 0) {
            quantity = 1;
        }

        updateTotalPrice();
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
        updateTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        updateTotalPrice();
        return totalPrice;
    }

    private void updateTotalPrice() {
        if (product == null) {
            totalPrice = BigDecimal.ZERO;
            return;
        }

        totalPrice = product.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof CartItem)) return false;

        final CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}