package my.spring.project.springmvc.controller;

import my.spring.project.springmvc.config.WebApplicationContextConfig;
import my.spring.project.springmvc.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
class ProductControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    void getProducts() throws Exception {
        mockMvc.perform(get("/market/products"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void getProductById() throws Exception {
        final Product product = new Product("P1234", "Test Product", new BigDecimal(500));

        mockMvc.perform(get("/market/product").param("id", "P1234"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product));
    }
}