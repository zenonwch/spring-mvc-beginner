package my.spring.project.springmvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class Customer {
    private String customerId;
    private String name;
    private String address;
    private long noOfOrdersMade;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        final Customer customer = (Customer) o;

        return customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}