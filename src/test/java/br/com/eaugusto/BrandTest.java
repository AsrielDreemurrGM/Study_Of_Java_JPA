package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.BrandDAO;
import br.com.eaugusto.dao.interfaces.IBrandDAO;
import br.com.eaugusto.domain.Brand;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 19, 2025
 */
public class BrandTest {

	private final IBrandDAO brandDao;
	private Brand brand;

	public BrandTest() {
		brandDao = new BrandDAO();
	}

	/**
	 * Initializes a Brand instance before each test.
	 */
	@BeforeEach
	public void createBrand() {
		brand = new Brand();
		brand.setCode("BRAND456");
		brand.setName("Test Brand");
		brand.setSlogan("Test Brand Slogan");
	}

	/**
	 * Cleans up the Brand instance after each test.
	 */
	@AfterEach
	public void cleanupBrand() {
		brandDao.delete(brand);
	}

	/**
	 * Tests if a Brand can be successfully registered in the database.
	 */
	@Test
	public void registerTest() {
		brand = brandDao.register(brand);
		assertNotNull(brand);
		assertNotNull(brand.getId());
	}

	/**
	 * Tests if a Brand can be retrieved by its ID.
	 */
	@Test
	public void searchByIdTest() {
		brand = brandDao.register(brand);

		Brand databaseBrand = brandDao.searchById(Brand.class, brand.getId());

		assertNotNull(databaseBrand);
		assertEquals(brand.getId(), databaseBrand.getId());
		assertEquals(brand.getCode(), databaseBrand.getCode());
		assertEquals(brand.getName(), databaseBrand.getName());
		assertEquals(brand.getSlogan(), databaseBrand.getSlogan());
	}

	/**
	 * Tests if all registered Brands can be retrieved.
	 */
	@Test
	public void searchAllTest() {
		brand = brandDao.register(brand);

		List<Brand> brandList = brandDao.searchAll(Brand.class);

		assertNotNull(brandList);
		assertFalse(brandList.isEmpty());

		Brand databaseBrand = brandList.get(0);

		assertNotNull(databaseBrand);
		assertEquals(brand.getId(), databaseBrand.getId());
		assertEquals(brand.getCode(), databaseBrand.getCode());
		assertEquals(brand.getName(), databaseBrand.getName());
		assertEquals(brand.getSlogan(), databaseBrand.getSlogan());
	}

	/**
	 * Tests if a Brand can be updated correctly.
	 */
	@Test
	public void updateTest() {
		brand = brandDao.register(brand);

		brand.setCode("BRAND123");
		brand.setName("New Brand Name");
		brand.setSlogan("New Test Brand Slogan");

		Brand updatedBrand = brandDao.update(brand);

		assertNotNull(updatedBrand);
		assertEquals(brand.getId(), updatedBrand.getId());
		assertEquals(brand.getCode(), updatedBrand.getCode());
		assertEquals(brand.getName(), updatedBrand.getName());
		assertEquals(brand.getSlogan(), updatedBrand.getSlogan());

		Brand databaseBrand = brandDao.searchById(Brand.class, updatedBrand.getId());

		assertEquals(brand.getId(), databaseBrand.getId());
		assertEquals(brand.getCode(), databaseBrand.getCode());
		assertEquals(brand.getName(), databaseBrand.getName());
		assertEquals(brand.getSlogan(), updatedBrand.getSlogan());
	}

	/**
	 * Tests if a Brand can be deleted and is no longer found in the database.
	 */
	@Test
	public void deleteTest() {
		Brand temporaryBrand = new Brand();
		temporaryBrand.setCode("BRAND1098");
		temporaryBrand.setName("Delete Test - Brand Name");
		temporaryBrand.setSlogan("Delete Test - Brand Slogan");

		temporaryBrand = brandDao.register(temporaryBrand);
		brandDao.delete(temporaryBrand);

		Brand searchBrand = brandDao.searchById(Brand.class, temporaryBrand.getId());
		assertNull(searchBrand);
	}
}
