package my.spring.project.springmvc.domain.repository.impl;

import my.spring.project.springmvc.domain.Address;
import my.spring.project.springmvc.domain.Buyer;
import my.spring.project.springmvc.domain.Order;
import my.spring.project.springmvc.domain.ShippingDetail;
import my.spring.project.springmvc.domain.repository.OrderRepository;
import my.spring.project.springmvc.service.CartService;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CartService cartService;

    public InMemoryOrderRepository(final NamedParameterJdbcTemplate jdbcTemplate,
                                   final CartService cartService) {
        this.jdbcTemplate = jdbcTemplate;
        this.cartService = cartService;
    }

    @Override
    public long saveOrder(final Order order) {
        final long buyerId = saveBuyer(order.getBuyer());
        order.getBuyer().setBuyerId(buyerId);

        final long shippingDetailId = saveShippingDetail(order.getShippingDetail());
        order.getShippingDetail().setId(shippingDetailId);

        final long createdOrderId = createOrder(order);
        cartService.clearCart(order.getCart().getId());

        return createdOrderId;
    }

    private long saveBuyer(final Buyer buyer) {
        final long addressId = saveAddress(buyer.getBillingAddress());

        final Map<String, Object> params = new HashMap<>();
        params.put("name", buyer.getName());
        params.put("phoneNumber", buyer.getPhoneNumber());
        params.put("billingAddressId", addressId);

        final SqlParameterSource paramSource = new MapSqlParameterSource(params);
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        final String insertBuyerQuery = "INSERT INTO CUSTOMER (NAME, PHONE_NUMBER, BILLING_ADDRESS_ID) " +
                "VALUES (:name, :phoneNumber, :billingAddressId)";
        jdbcTemplate.update(insertBuyerQuery, paramSource, keyHolder, new String[]{"ID"});

        return (int) keyHolder.getKey();
    }

    private long saveShippingDetail(final ShippingDetail shippingDetail) {
        final long addressId = saveAddress(shippingDetail.getShippingAddress());

        final Map<String, Object> params = new HashMap<>();
        params.put("name", shippingDetail.getName());
        params.put("shippingDate", shippingDetail.getShippingDate());
        params.put("shippingAddress", addressId);

        final SqlParameterSource paramSource = new MapSqlParameterSource(params);
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        final String insertShippingDetailQuery = "INSERT INTO SHIPPING_DETAIL (NAME, SHIPPING_DATE, SHIPPING_ADDRESS_ID) " +
                "VALUES (:name, :shippingDate, :shippingAddress)";
        jdbcTemplate.update(insertShippingDetailQuery, paramSource, keyHolder, new String[]{"ID"});

        return (int) keyHolder.getKey();
    }

    private long saveAddress(final Address address) {
        final Map<String, Object> params = new HashMap<>();
        params.put("doorNo", address.getDoorNo());
        params.put("streetName", address.getStreetName());
        params.put("areaName", address.getAreaName());
        params.put("state", address.getState());
        params.put("country", address.getCountry());
        params.put("zip", address.getZipCode());

        final SqlParameterSource paramSource = new MapSqlParameterSource(params);
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        final String insertAddressQuery = "INSERT INTO ADDRESS (DOOR_NO, STREET_NAME, AREA_NAME, STATE, COUNTRY, ZIP) " +
                "VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip)";
        jdbcTemplate.update(insertAddressQuery, paramSource, keyHolder, new String[]{"ID"});

        return (int) keyHolder.getKey();
    }

    private long createOrder(final Order order) {
        final Map<String, Object> params = new HashMap<>();
        params.put("cartId", order.getCart().getId());
        params.put("buyerId", order.getBuyer().getBuyerId());
        params.put("shippingDetailId", order.getShippingDetail().getId());

        final SqlParameterSource paramSource = new MapSqlParameterSource(params);
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        final String insertOrderQuery = "INSERT INTO ORDERS (CART_ID, CUSTOMER_ID, SHIPPING_DETAIL_ID) " +
                "VALUES (:cartId, :buyerId, :shippingDetailId)";
        jdbcTemplate.update(insertOrderQuery, paramSource, keyHolder, new String[]{"ID"});

        return (int) keyHolder.getKey();
    }
}