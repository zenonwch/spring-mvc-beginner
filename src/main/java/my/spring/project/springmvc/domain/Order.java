package my.spring.project.springmvc.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class Order implements Serializable {
    private static final long serialVersionUID = 0L;

    @NumberFormat
    private long orderId;
    private Cart cart;
    private Buyer buyer;
    private ShippingDetail shippingDetail;

    public Order() {
        buyer = new Buyer();
        shippingDetail = new ShippingDetail();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Order)) return false;

        final Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}