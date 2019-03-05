package my.spring.project.springmvc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import my.spring.project.springmvc.validator.ProductId;
import my.spring.project.springmvc.validator.ValidCategory;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    private static final long serialVersionUID = 0L;

    @ProductId
    @NotNull(message = "{pattern.product.productId.validation}")
    @Pattern(regexp = "P[0-9]+", message = "{pattern.product.productId.validation}")
    private String productId;

    @Size(min = 4, max = 50, message = "{pattern.product.name.validation}")
    @NotNull(message = "{pattern.product.name.validation}")
    private String name;

    @Min(value = 0, message = "{pattern.product.unitPrice.validation.min}")
    @Digits(integer = 8, fraction = 2, message = "{pattern.product.unitPrice.validation.digits}")
    @NotNull(message = "{pattern.product.unitPrice.validation.notNull}")
    private BigDecimal unitPrice;
    private String description;

    @NotBlank(message = "{pattern.product.manufacturer.validation}")
    private String manufacturer;

    @ValidCategory
    @NotBlank(message = "{pattern.product.category.validation}")
    private String category;

    @Min(value = 0, message = "{pattern.product.unitsInStock.validation}")
    @NotNull(message = "{pattern.product.unitsInStock.validation}")
    private long unitsInStock;

    @Min(value = 0, message = "{pattern.product.unitsInOrder.validation}")
    @NotNull(message = "{pattern.product.unitsInStock.validation}")
    private long unitsInOrder;
    private boolean discontinued;
    private String condition;
    @JsonIgnore
    private transient MultipartFile productImage;
    @JsonIgnore
    private transient MultipartFile productGuide;

    public Product(final String productId, final String name, final BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        final Product product = (Product) o;

        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}