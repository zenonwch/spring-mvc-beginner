package my.spring.project.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class Address implements Serializable {
    private static final long serialVersionUID = 0L;

    private long id;
    private String doorNo;
    private String streetName;
    private String areaName;
    private String state;
    private String country;
    private String zipCode;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Address)) return false;

        final Address address = (Address) o;
        return id == address.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}