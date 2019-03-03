package my.spring.project.springmvc.domain.repository.impl;

import my.spring.project.springmvc.domain.Cart;
import my.spring.project.springmvc.domain.CartItem;
import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.domain.repository.CartRepository;
import my.spring.project.springmvc.domain.repository.impl.mapper.CartMapper;
import my.spring.project.springmvc.service.ProductService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Repository
public class InMemoryCartRepository implements CartRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ProductService productService;

    public InMemoryCartRepository(final NamedParameterJdbcTemplate jdbcTemplate,
                                  final ProductService productService) {
        this.jdbcTemplate = jdbcTemplate;
        this.productService = productService;
    }

    @Override
    public void create(final Cart cart) {
        final Map<String, Object> cartParams = new HashMap<>();
        cartParams.put("id", cart.getId());

        final String insertCartQuery = "INSERT INTO CART (ID) VALUES (:id)";
        jdbcTemplate.update(insertCartQuery, cartParams);

        final Consumer<CartItem> createCartItem = ci -> createCartItem(ci, cart.getId());
        cart.getCartItems().forEach(createCartItem);
    }

    @Override
    public Cart read(final String id) {
        try {
            final String selectCartByIdQuery = "SELECT * FROM CART WHERE ID = :id";
            final CartMapper cartMapper = new CartMapper(productService, jdbcTemplate);

            return jdbcTemplate.queryForObject(selectCartByIdQuery, Collections.singletonMap("id", id), cartMapper);
        } catch (final EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public void update(final Cart cart) {
        cart.getCartItems().forEach(ci -> {
            if (isNewItem(ci.getId())) {
                createCartItem(ci, cart.getId());
            } else {
                updateCartItem(ci, cart.getId());
            }
        });
    }

    @Override
    public void delete(final String id) {
        clearCart(id);

        final String deleteCartQuery = "DELETE FROM CART WHERE ID = :id";
        jdbcTemplate.update(deleteCartQuery, Collections.singletonMap("id", id));
    }

    @Override
    public void clearCart(final String id) {
        final String deleteCartItemsQuery = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
        jdbcTemplate.update(deleteCartItemsQuery, Collections.singletonMap("id", id));
    }

    @Override
    public void removeItem(final String cartId, final String productId) {
        final Map<String, Object> params = new HashMap<>();
        params.put("cartId", cartId);
        params.put("productId", productId);

        final String deleteCartItemQuery = "DELETE FROM CART_ITEM " +
                "WHERE PRODUCT_ID = :productId AND CART_ID = :cartId";

        jdbcTemplate.update(deleteCartItemQuery, params);
    }

    private boolean isNewItem(final String cartItemId) {
        final String countCartItemQuery = "SELECT count(*) FROM CART_ITEM WHERE ID = :id";
        final Integer count = jdbcTemplate.queryForObject(countCartItemQuery, Collections.singletonMap("id", cartItemId), Integer.class);
        return count == null || count == 0;
    }

    private void createCartItem(final CartItem cartItem, final String cartId) {
        final Map<String, Object> params = collectCartItemParams(cartItem, cartId);

        final String insertCartItemQuery = "INSERT INTO CART_ITEM (ID, PRODUCT_ID, CART_ID, QUANTITY) " +
                "VALUES (:id, :productId, :cartId, :quantity)";

        jdbcTemplate.update(insertCartItemQuery, params);
    }

    private void updateCartItem(final CartItem cartItem, final String cartId) {
        final Map<String, Object> params = collectCartItemParams(cartItem, cartId);

        final String updateCartItemQuery = "UPDATE CART_ITEM " +
                "SET QUANTITY = :quantity, " +
                "PRODUCT_ID = :productId " +
                "WHERE ID = :id AND CART_ID = :cartId";

        jdbcTemplate.update(updateCartItemQuery, params);
    }

    private Map<String, Object> collectCartItemParams(final CartItem ci, final String cartId) {
        final Product product = productService.getProductById(ci.getProductId());

        final Map<String, Object> cartItemParams = new HashMap<>();
        cartItemParams.put("id", ci.getId());
        cartItemParams.put("productId", product.getProductId());
        cartItemParams.put("cartId", cartId);
        cartItemParams.put("quantity", ci.getQuantity());
        return cartItemParams;
    }
}