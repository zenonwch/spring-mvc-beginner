package my.spring.project.springmvc.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class CartItemDto implements Serializable {
    private static final long serialVersionUID = 0L;

    private String id;
    private String productId;
    private String name;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}