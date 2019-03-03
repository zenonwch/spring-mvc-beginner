package my.spring.project.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class Buyer implements Serializable {
    private static final long serialVersionUID = 0L;

    private long buyerId;
    private String name;
    private Address billingAddress;
    private String phoneNumber;

    Buyer() {
        billingAddress = new Address();
    }

    public Buyer(final long buyerId, final String name) {
        this();
        this.buyerId = buyerId;
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Buyer)) return false;

        final Buyer buyer = (Buyer) o;
        return buyerId == buyer.buyerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerId);
    }
}