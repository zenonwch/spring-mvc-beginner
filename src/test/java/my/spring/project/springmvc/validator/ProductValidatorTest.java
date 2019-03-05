package my.spring.project.springmvc.validator;

import my.spring.project.springmvc.config.WebApplicationContextConfig;
import my.spring.project.springmvc.domain.Category;
import my.spring.project.springmvc.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
class ProductValidatorTest {
    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    void validProduct() {
        final Product product = createTestProduct("P001", "Test Product", new BigDecimal(100), "Test Manufacturer", Category.Laptop.name(), 5, 0);

        final BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(0, bindException.getErrorCount());
    }

    @Test
    void productWithInvalidProductId() {
        final Product product = createTestProduct(null, "Test Product", new BigDecimal(100), "Test Manufacturer", Category.Laptop.name(), 5, 0);

        BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("productId"));
        assertEquals("Invalid Product ID. It should start with character 'P' followed by number.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertNull(bindException.getFieldValue("productId"));

        Locale.setDefault(new Locale("be"));
        product.setProductId("");
        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("productId"));
        assertEquals("Няправільны ID прадукту. Ён павінен пачынацца з знака 'P', за якім варта лік.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("", bindException.getFieldValue("productId"));

        Locale.setDefault(new Locale("ru"));
        product.setProductId("1234P");
        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("productId"));
        assertEquals("Неверный ID продукта. Он должен начинаться с символа 'P', за которым следует число.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("1234P", bindException.getFieldValue("productId"));
    }

    @Test
    void productWithExistingId() {
        final Product product = createTestProduct("P1234", "Test Product", new BigDecimal(100), "Test Manufacturer", Category.Laptop.name(), 5, 0);

        final BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("productId"));
        assertEquals("A product already exists with this product id.", bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("P1234", bindException.getFieldValue("productId"));
    }

    @Test
    void productWithInvalidName() {
        final Product product = createTestProduct("P0001", null, new BigDecimal(100), "Test Manufacturer", Category.Laptop.name(), 5, 0);

        BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("name"));
        assertEquals("Invalid Product Name. It should be minimum 4 to maximum 50 characters long.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertNull(bindException.getFieldValue("name"));

        Locale.setDefault(new Locale("be"));
        product.setName("");

        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("name"));
        assertEquals("Няправільная назва прадукту. Даўжыня павінна быць не менш за 4 і не больш за 50 знакаў.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("", bindException.getFieldValue("name"));

        Locale.setDefault(new Locale("ru"));
        product.setName("Two");

        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("name"));
        assertEquals("Неверное название продукта. Длина должна быть не менее 4 и не более 50 символов.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("Two", bindException.getFieldValue("name"));

        product.setName("Very Long Product Name. Longer than maximum allowed value.");

        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("name"));
        assertEquals("Неверное название продукта. Длина должна быть не менее 4 и не более 50 символов.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("Very Long Product Name. Longer than maximum allowed value.", bindException.getFieldValue("name"));
    }

    @Test
    void productWithInvalidUnitPrice() {
        final Product product = createTestProduct("P0001", "Test Product", null, "Test Manufacturer", Category.Laptop.name(), 5, 0);

        BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("unitPrice"));
        assertEquals("Unit Price is Invalid. It cannot be empty.", bindException.getFieldErrors().get(0).getDefaultMessage());
        assertNull(bindException.getFieldValue("unitPrice"));

        product.setUnitPrice(new BigDecimal(-5));
        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("unitPrice"));
        assertEquals("Unit Price is Invalid. It cannot be negative value.", bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals(new BigDecimal("-5"), bindException.getFieldValue("unitPrice"));

        Locale.setDefault(new Locale("be"));
        product.setUnitPrice(new BigDecimal("0.1234"));

        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("unitPrice"));
        assertEquals("Няправільны фармат цаны. Можа мець максімум 8 лічбаў цэлага ліку і 2 дробавая лічбы.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals(new BigDecimal("0.1234"), bindException.getFieldValue("unitPrice"));

        Locale.setDefault(new Locale("ru"));
        product.setUnitPrice(new BigDecimal("123456789.12"));

        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("unitPrice"));
        assertEquals("Неверный формат цены. Может содержать максимум 8 цифр целого числа и 2 дробные цифры.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals(new BigDecimal("123456789.12"), bindException.getFieldValue("unitPrice"));

        assertThrows(NumberFormatException.class, () -> product.setUnitPrice(new BigDecimal("test")));
    }

    @Test
    void productWithEmptyManufacturer() {
        final Product product = createTestProduct("P0001", "Test Product", new BigDecimal(100), null, Category.Laptop.name(), 5, 0);

        BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("manufacturer"));
        assertEquals("Manufacturer is Invalid. It cannot be empty.", bindException.getFieldErrors().get(0).getDefaultMessage());
        assertNull(bindException.getFieldValue("manufacturer"));

        Locale.setDefault(new Locale("be"));
        product.setManufacturer("");

        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("manufacturer"));
        assertEquals("Няправільная назва вытворцы. Не можа быць пустой.", bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("", bindException.getFieldValue("manufacturer"));
    }

    @Test
    void productWithInvalidCategory() {
        final Product product = createTestProduct("P0001", "Test Product", new BigDecimal(100), "Manufacturer", null, 5, 0);

        BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("category"));
        assertEquals("Category is Invalid. It cannot be empty.", bindException.getFieldErrors().get(0).getDefaultMessage());
        assertNull(bindException.getFieldValue("category"));

        Locale.setDefault(new Locale("be"));
        product.setCategory("");

        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("category"));
        assertEquals("Няправільная назва катэгорыі. Не можа быць пустой.",
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("", bindException.getFieldValue("category"));

        Locale.setDefault(new Locale("ru"));
        product.setCategory("Invalid Category");

        bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("category"));
        assertEquals("Категория должна быть одной из следующих: " + Category.CATEGORIES + '.',
                bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals("Invalid Category", bindException.getFieldValue("category"));
    }

    @Test
    void productWithNegativeUnitsInStock() {
        final Product product = createTestProduct("P0001", "Test Product", new BigDecimal(100), "Manufacturer", Category.Laptop.name(), -5, 0);

        final BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("unitsInStock"));
        assertEquals("Units In Stock is Invalid. It cannot be negative value.", bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals(-5L, bindException.getFieldValue("unitsInStock"));
    }

    @Test
    void productWithNegativeUnitsInOrder() {
        final Product product = createTestProduct("P0001", "Test Product", new BigDecimal(100), "Manufacturer", Category.Laptop.name(), 0, -1);

        final BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("unitsInOrder"));
        assertEquals("Units In Order is Invalid. It cannot be negative value.", bindException.getFieldErrors().get(0).getDefaultMessage());
        assertEquals(-1L, bindException.getFieldValue("unitsInOrder"));
    }

    @Test
    void productWithBigQuantityAndHighPrice() {
        final Product product = createTestProduct("P0001", "Test Product", new BigDecimal(1000), "Manufacturer", Category.Laptop.name(), 100, 0);

        final BindException bindException = new BindException(product, "product");
        ValidationUtils.invokeValidator(productValidator, product, bindException);

        assertEquals(1, bindException.getErrorCount());
        assertEquals(1, bindException.getFieldErrorCount("unitsInStock"));
        assertEquals("You cannot add more than 99 units if the unit price is greater than 1,000.",
                messageSource.getMessage(bindException.getFieldError("unitsInStock"), Locale.ENGLISH));
        assertEquals("Вы не можаце дадаць больш, чым 99 шт., калі кошт за штуку больш, чым 1 000.",
                messageSource.getMessage(bindException.getFieldError("unitsInStock"), new Locale("be")));
        assertEquals("Вы не можете добавить более 99 шт., если цена за штуку превышает 1 000.",
                messageSource.getMessage(bindException.getFieldError("unitsInStock"), new Locale("ru")));
        assertEquals(100L, bindException.getFieldValue("unitsInStock"));
    }

    @SuppressWarnings("SameParameterValue")
    private Product createTestProduct(final String productId, final String name, final BigDecimal unitPrice,
                                      final String manufacturer, final String category, final long unitsInStock, final long unitsInOrder) {
        final Product product = new Product();
        product.setProductId(productId);
        product.setName(name);
        product.setUnitPrice(unitPrice);
        product.setManufacturer(manufacturer);
        product.setCategory(category);
        product.setUnitsInStock(unitsInStock);
        product.setUnitsInOrder(unitsInOrder);

        return product;
    }
}