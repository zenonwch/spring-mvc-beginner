package my.spring.project.springmvc.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.spring.project.springmvc.config.WebApplicationContextConfig;
import my.spring.project.springmvc.dto.CartDto;
import my.spring.project.springmvc.dto.CartItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
class CartRestControllerTest {
    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private MockHttpSession mockSession;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void cartCRUD() throws Exception {
        final CartDto cartDto = new CartDto();
        cartDto.setId(mockSession.getId());

        mockMvc.perform(post("/rest/cart").session(mockSession)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"" + mockSession.getId() + "\",\"cartItems\":[],\"grandTotal\":0}"));

        cartDto.setGrandTotal(new BigDecimal(500));
        mockMvc.perform(put("/rest/cart/" + mockSession.getId()).session(mockSession)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"" + mockSession.getId() + "\",\"cartItems\":[],\"grandTotal\":0}"));

        final CartItemDto cartItem = new CartItemDto();
        cartItem.setProductId("P1234");
        cartItem.setQuantity(2);
        cartItem.setUnitPrice(new BigDecimal(5000));
        cartItem.setName("Some Test Name");
        cartItem.setTotalPrice(new BigDecimal(10));
        cartItem.setId("Fake ID");

        cartDto.setCartItems(Collections.singletonList(cartItem));
        cartDto.setId(mockSession.getId());
        cartDto.setGrandTotal(new BigDecimal(12345));
        mockMvc.perform(put("/rest/cart/" + mockSession.getId()).session(mockSession)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems[0].id").value(is(not("Fake ID"))))
                .andExpect(jsonPath("$.cartItems[0].productId").value("P1234"))
                .andExpect(jsonPath("$.cartItems[0].name").value("iPhone XS"))
                .andExpect(jsonPath("$.cartItems[0].unitPrice").value("1500"))
                .andExpect(jsonPath("$.cartItems[0].quantity").value("2"))
                .andExpect(jsonPath("$.cartItems[0].totalPrice").value("3000"))
                .andExpect(jsonPath("$.grandTotal").value("3000"));

        final CartItemDto cartItem2 = new CartItemDto();
        cartItem2.setProductId("P1235");

        cartDto.setCartItems(Arrays.asList(cartItem, cartItem2));
        cartDto.setGrandTotal(new BigDecimal(12345));
        mockMvc.perform(put("/rest/cart/" + mockSession.getId()).session(mockSession)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems", hasSize(2)))
                .andExpect(jsonPath("$.cartItems[1].productId").value("P1235"))
                .andExpect(jsonPath("$.cartItems[1].quantity").value("1"))
                .andExpect(jsonPath("$.cartItems[1].totalPrice").value("700"))
                .andExpect(jsonPath("$.grandTotal").value("3700"));

        mockMvc.perform(delete("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void addProductToCart() throws Exception {
        mockMvc.perform(put("/rest/cart/add/P1234").session(mockSession))
                .andExpect(status().is(200));
        mockMvc.perform(get("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems[0].productId").value("P1234"));
    }

    @Test
    void removeProductFromCart() throws Exception {
        final CartDto cartDto = new CartDto();
        cartDto.setId(mockSession.getId());

        final CartItemDto cartItem1 = new CartItemDto();
        cartItem1.setProductId("P1234");
        cartItem1.setQuantity(2);

        final CartItemDto cartItem2 = new CartItemDto();
        cartItem2.setProductId("P1235");
        cartDto.setCartItems(Arrays.asList(cartItem1, cartItem2));

        mockMvc.perform(post("/rest/cart").session(mockSession)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems", hasSize(2)))
                .andExpect(jsonPath("$.grandTotal").value("3700"));

        mockMvc.perform(put("/rest/cart/remove/P1234").session(mockSession))
                .andExpect(status().is(200));
        mockMvc.perform(get("/rest/cart/" + mockSession.getId()).session(mockSession))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems", hasSize(1)))
                .andExpect(jsonPath("$.grandTotal").value("700"));
    }
}