package my.spring.project.springmvc.domain.repository.impl.mapper;

import my.spring.project.springmvc.domain.CartItem;
import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMapper implements RowMapper<CartItem> {
    private final ProductService productService;

    CartItemMapper(final ProductService productService) {
        this.productService = productService;
    }

    @Override
    public CartItem mapRow(final ResultSet rs, final int i) throws SQLException {
        final String id = rs.getString("ID");
        final CartItem cartItem = new CartItem(id);

        final String productId = rs.getString("PRODUCT_ID");
        final Product product = productService.getProductById(productId);
        cartItem.setProductId(product.getProductId());
        cartItem.setProduct(product);

        final int quantity = rs.getInt("QUANTITY");
        cartItem.setQuantity(quantity);

        return cartItem;
    }
}