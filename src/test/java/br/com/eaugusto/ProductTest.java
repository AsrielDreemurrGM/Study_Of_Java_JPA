package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.ProductDAO;
import br.com.eaugusto.dao.interfaces.IProductDAO;
import br.com.eaugusto.domain.Product;

/**
 * Unit tests for {@link br.com.eaugusto.domain.Product} entity using
 * {@link br.com.eaugusto.dao.ProductDAO}.
 *
 * <p>
 * This test class ensures that all core CRUD functionalities are working as
 * expected for {@link Product} entities, including handling of date-time
 * attributes and numerical values.
 * </p>
 *
 * <p>
 * Time-based fields are compared using
 * {@link java.time.temporal.ChronoUnit#MILLIS} for accuracy.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 16, 2025
 */
public class ProductTest {

	private final IProductDAO productDao;
	private Product product;

	public ProductTest() {
		productDao = new ProductDAO();
	}

	/**
	 * Initializes a Product instance before each test.
	 */
	@BeforeEach
	public void createProduct() {
		product = new Product();
		product.setCode("XYZ123");
		product.setName("Test Product");
		product.setDescription("Java-Test-Product");
		product.setPrice(1000.00);
		product.setStockQuantity(15);
		product.setCreationDate(Instant.now());
	}

	/**
	 * Cleans up the Product instance after each test.
	 */
	@AfterEach
	public void cleanupProduct() {
		productDao.delete(product);
	}

	/**
	 * Tests if a Product can be successfully registered in the database.
	 */
	@Test
	public void registerTest() {
		product = productDao.register(product);
		assertNotNull(product);
		assertNotNull(product.getId());
	}

	/**
	 * Tests if a Product can be retrieved by its ID.
	 */
	@Test
	public void searchByIdTest() {
		product = productDao.register(product);

		Product databaseProduct = productDao.searchById(Product.class, product.getId());

		assertNotNull(databaseProduct);
		assertEquals(product.getId(), databaseProduct.getId());
		assertEquals(product.getCode(), databaseProduct.getCode());
		assertEquals(product.getName(), databaseProduct.getName());
		assertEquals(product.getDescription(), databaseProduct.getDescription());
		assertEquals(product.getPrice(), databaseProduct.getPrice());
		assertEquals(product.getStockQuantity(), databaseProduct.getStockQuantity());
		assertEquals(product.getCreationDate().truncatedTo(ChronoUnit.MILLIS),
				databaseProduct.getCreationDate().truncatedTo(ChronoUnit.MILLIS));
	}

	/**
	 * Tests if all registered Products can be retrieved.
	 */
	@Test
	public void searchAllTest() {
		product = productDao.register(product);

		List<Product> productList = productDao.searchAll(Product.class);

		assertNotNull(productList);
		assertFalse(productList.isEmpty());

		Product databaseProduct = productList.get(0);

		assertNotNull(databaseProduct);
		assertEquals(product.getId(), databaseProduct.getId());
		assertEquals(product.getCode(), databaseProduct.getCode());
		assertEquals(product.getName(), databaseProduct.getName());
		assertEquals(product.getDescription(), databaseProduct.getDescription());
		assertEquals(product.getPrice(), databaseProduct.getPrice());
		assertEquals(product.getStockQuantity(), databaseProduct.getStockQuantity());
		assertEquals(product.getCreationDate().truncatedTo(ChronoUnit.MILLIS),
				databaseProduct.getCreationDate().truncatedTo(ChronoUnit.MILLIS));
	}

	/**
	 * Tests if a Product can be updated correctly.
	 */
	@Test
	public void updateTest() {
		product = productDao.register(product);

		product.setCode("ABC321");
		product.setName("New Product Name");
		product.setDescription("Temporary-Java-Update-Product");
		product.setPrice(2000.00);
		product.setStockQuantity(30);
		product.setCreationDate(Instant.now());

		Product updatedProduct = productDao.update(product);

		assertNotNull(updatedProduct);
		assertEquals(product.getId(), updatedProduct.getId());
		assertEquals(product.getCode(), updatedProduct.getCode());
		assertEquals(product.getName(), updatedProduct.getName());
		assertEquals(product.getDescription(), updatedProduct.getDescription());
		assertEquals(product.getPrice(), updatedProduct.getPrice());
		assertEquals(product.getStockQuantity(), updatedProduct.getStockQuantity());
		assertEquals(product.getCreationDate().truncatedTo(ChronoUnit.MILLIS),
				updatedProduct.getCreationDate().truncatedTo(ChronoUnit.MILLIS));

		Product databaseProduct = productDao.searchById(Product.class, updatedProduct.getId());

		assertEquals(product.getCode(), databaseProduct.getCode());
		assertEquals(product.getName(), databaseProduct.getName());
		assertEquals(product.getDescription(), databaseProduct.getDescription());
		assertEquals(product.getPrice(), databaseProduct.getPrice());
		assertEquals(product.getStockQuantity(), databaseProduct.getStockQuantity());
		assertEquals(product.getCreationDate().truncatedTo(ChronoUnit.MILLIS),
				databaseProduct.getCreationDate().truncatedTo(ChronoUnit.MILLIS));
	}

	/**
	 * Tests if a Product can be deleted and is no longer found in the database.
	 */
	@Test
	public void deleteTest() {
		Product temporaryProduct = new Product();
		temporaryProduct.setCode("DEF567");
		temporaryProduct.setName("Delete Test Product Name");
		temporaryProduct.setDescription("Delete-Test-Product-Description");
		temporaryProduct.setPrice(500.00);
		temporaryProduct.setStockQuantity(10);
		temporaryProduct.setCreationDate(Instant.now());

		temporaryProduct = productDao.register(temporaryProduct);
		productDao.delete(temporaryProduct);

		Product result = productDao.searchById(Product.class, temporaryProduct.getId());
		assertNull(result);
	}
}
