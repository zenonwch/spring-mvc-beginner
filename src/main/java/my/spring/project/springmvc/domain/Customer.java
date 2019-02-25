package my.spring.project.springmvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Customer implements Serializable {
    private static final long serialVersionUID = 0L;

    @Pattern(regexp = "C[0-9]+", message = "{pattern.customer.customerId.validation}")
    private String customerId;

    @NotBlank(message = "{pattern.customer.name.validation}")
    private String name;

    @NotBlank(message = "{pattern.customer.address.validation}")
    private String address;

    @Min(value = 0, message = "{pattern.customer.madeOrders.validation}")
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