package my.spring.project.springmvc.domain.repository.impl;

import my.spring.project.springmvc.domain.Product;
import my.spring.project.springmvc.domain.repository.ProductRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private static final String SELECT_ALL = "SELECT * FROM PRODUCTS";
    private static final String SELECT_BY_CATEGORY = "SELECT * FROM PRODUCTS WHERE CATEGORY = :category";
    private static final String SELECT_BY_FILTER = "SELECT * FROM PRODUCTS WHERE CATEGORY IN (:categories) AND MANUFACTURER IN (:brands)";
    private static final String SELECT_BY_ID = "SELECT * FROM PRODUCTS WHERE ID = :id";
    private static final String SELECT_BY_CATEGORY_BRAND_PRICE =
            "SELECT * FROM PRODUCTS WHERE CATEGORY = :category AND MANUFACTURER = :brand AND UNIT_PRICE BETWEEN :low AND :high";
    private static final String INSERT = "INSERT INTO PRODUCTS VALUES " +
            "(:id, :name, :desc, :price, :manufacturer, :category, :condition, :inStock, :inOrder, :discontinued)";
    private static final String UPDATE_STOCK = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public InMemoryProductRepository(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SELECT_ALL, Collections.emptyMap(), new ProductMapper());
    }

    @Override
    public List<Product> getProductsByCategory(final String category) {
        final Map<String, String> params = Collections.singletonMap("category", category);
        return jdbcTemplate.query(SELECT_BY_CATEGORY, params, new ProductMapper());
    }

    @Override
    public List<Product> getProductsByFilter(final Map<String, List<String>> filterParams) {
        return jdbcTemplate.query(SELECT_BY_FILTER, filterParams, new ProductMapper());
    }

    @Override
    public List<Product> filterProducts(final String category, final String brand, final Map<String, BigDecimal> price) {
        final Map<String, Object> filterParams = new HashMap<>(price);
        filterParams.put("category", category);
        filterParams.put("brand", brand);

        return jdbcTemplate.query(SELECT_BY_CATEGORY_BRAND_PRICE, filterParams, new ProductMapper());
    }

    @Override
    public Product getProductById(final String productId) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, Collections.singletonMap("id", productId), new ProductMapper());
    }

    @Override
    public void addProduct(final Product product) {
        final Map<String, Object> params = composeParams(product);

        jdbcTemplate.update(INSERT, params);
    }

    @Override
    public void updateStock(final String productId, final long noOfUnits) {
        final Map<String, Object> params = new HashMap<>();
        params.put("unitsInStock", noOfUnits);
        params.put("id", productId);

        jdbcTemplate.update(UPDATE_STOCK, params);
    }

    private Map<String, Object> composeParams(final Product product) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", product.getProductId());
        params.put("name", product.getName());
        params.put("desc", product.getDescription());
        params.put("price", product.getUnitPrice());
        params.put("manufacturer", product.getManufacturer());
        params.put("category", product.getCategory());
        params.put("condition", product.getCondition());
        params.put("inStock", product.getUnitsInStock());
        params.put("inOrder", product.getUnitsInOrder());
        params.put("discontinued", product.isDiscontinued());

        return params;
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