package my.spring.project.springmvc.domain.repository.impl.mapper;

import lombok.NoArgsConstructor;
import my.spring.project.springmvc.domain.Cart;
import my.spring.project.springmvc.domain.CartItem;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
public class CartMapper implements RowMapper<Cart> {
    private CartItemMapper cartItemMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public CartMapper(final ProductService productService,
                      final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        cartItemMapper = new CartItemMapper(productService);
    }

    @Override
    public Cart mapRow(final ResultSet rs, final int i) throws SQLException {
        final String id = rs.getString("ID");
        final Cart cart = new Cart(id);

        final String selectCartItemsById = String.format("SELECT * FROM CART_ITEM WHERE CART_ID = '%s'", id);
        final List<CartItem> cartItems = jdbcTemplate.query(selectCartItemsById, cartItemMapper);
        cart.setCartItems(cartItems);

        return cart;
    }
}