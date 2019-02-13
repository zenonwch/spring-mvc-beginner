package my.spring.project.springmvc.domain.repository.impl;

import my.spring.project.springmvc.domain.Customer;
import my.spring.project.springmvc.domain.repository.CustomerRepository;
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
public class InMemoryCustomerRepository implements CustomerRepository {
    private static final String SELECT_ALL = "SELECT * FROM CUSTOMERS";
    private static final String INSERT = "INSERT INTO CUSTOMERS VALUES " +
            "(:id, :name, :address, :ordersMade)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public InMemoryCustomerRepository(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL, Collections.emptyMap(), new CustomerMapper());
    }

    @Override
    public void addCustomer(final Customer customer) {
        final Map<String, Object> params = composeParams(customer);

        jdbcTemplate.update(INSERT, params);
    }

    private Map<String, Object> composeParams(final Customer customer) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", customer.getCustomerId());
        params.put("name", customer.getName());
        params.put("address", customer.getAddress());
        params.put("ordersMade", customer.getNoOfOrdersMade());

        return params;
    }

    private static final class CustomerMapper implements RowMapper<Customer> {

        @Override
        @SuppressWarnings("NestedMethodCall")
        public Customer mapRow(final ResultSet rs, final int i) throws SQLException {
            final Customer customer = new Customer();
            customer.setCustomerId(rs.getString("ID"));
            customer.setName(rs.getString("NAME"));
            customer.setAddress(rs.getString("ADDRESS"));
            customer.setNoOfOrdersMade(rs.getLong("ORDERS_MADE"));

            return customer;
        }
    }
}