package my.spring.project.springmvc.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class ShippingDetail implements Serializable {
    private static final long serialVersionUID = 0L;

    private long id;
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy[ HH:mm:ss]")
    private LocalDateTime shippingDate;
    private Address shippingAddress;

    ShippingDetail() {
        shippingAddress = new Address();
    }

    @SuppressWarnings("unused")
    public void setShippingDate(final LocalDateTime shippingDate) {
        this.shippingDate = shippingDate == null
                ? LocalDateTime.now().plusDays(7)
                : shippingDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ShippingDetail)) return false;
        final ShippingDetail that = (ShippingDetail) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}