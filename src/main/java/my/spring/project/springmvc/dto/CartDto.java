package my.spring.project.springmvc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDto implements Serializable {
    private static final long serialVersionUID = 0L;

    private String id;
    private List<CartItemDto> cartItems;
    private BigDecimal grandTotal;

    public CartDto(final String id) {
        this.id = id;
        cartItems = new ArrayList<>();
    }
}