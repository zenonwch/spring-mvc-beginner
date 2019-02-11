package my.spring.project.springmvc.domain.repository.impl;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.domain.repository.ProductRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private static final String SELECT_ALL = "SELECT * FROM PRODUCTS";
    private static final String UPDATE_STOCK = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :unitsInStock WHERE id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public InMemoryProductRepository(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SELECT_ALL, Collections.emptyMap(), new ProductMapper());
    }

    @Override
    public void updateStock(final String productId, final long noOfUnits) {
        final Map<String, Object> params = new HashMap<>();
        params.put("unitsInStock", noOfUnits);
        params.put("id", productId);

        jdbcTemplate.update(UPDATE_STOCK, params);
    }

    private static final class ProductMapper implements RowMapper<Product> {

        @Override
        @SuppressWarnings("NestedMethodCall")
        public Product mapRow(final ResultSet rs, final int i) throws SQLException {
            final Product product = new Product();
            product.setProductId(rs.getString("ID"));
            product.setName(rs.getString("NAME"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
            product.setManufacturer(rs.getString("MANUFACTURER"));
            product.setCategory(rs.getString("CATEGORY"));
            product.setCondition(rs.getString("CONDITION"));
            product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
            product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
            product.setDiscontinued(rs.getBoolean("DISCONTINUED"));

            return product;
        }
    }
}