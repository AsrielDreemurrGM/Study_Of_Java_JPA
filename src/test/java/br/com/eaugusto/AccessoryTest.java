package br.com.eaugusto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.AccessoryDAO;
import br.com.eaugusto.dao.interfaces.IAccessoryDAO;
import br.com.eaugusto.domain.Accessory;

/**
 * Unit tests for {@link Accessory} entity using {@link AccessoryDAO}.
 * 
 * <p>
 * Tests cover all basic CRUD operations and ensure data consistency between
 * in-memory objects and the database.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 19, 2025
 */
public class AccessoryTest {

	private final IAccessoryDAO accessoryDao;
	private Accessory accessory;

	public AccessoryTest() {
		accessoryDao = new AccessoryDAO();
	}

	/**
	 * Initializes a Accessory instance before each test.
	 */
	@BeforeEach
	public void createAccessory() {
		accessory = new Accessory();
		accessory.setCode("ACC987");
		accessory.setName("Test Accessory");
		accessory.setPrice(300.00);
		accessory.setStockQuantity(13);
	}

	/**
	 * Cleans up the Accessory instance after each test.
	 */
	@AfterEach
	public void cleanupAccessory() {
		accessoryDao.delete(accessory);
	}

	/**
	 * Tests if a Accessory can be successfully registered in the database.
	 */
	@Test
	public void registerTest() {
		accessory = accessoryDao.register(accessory);
		assertNotNull(accessory);
		assertNotNull(accessory.getId());
	}

	/**
	 * Tests if an Accessory can be retrieved by its ID.
	 */
	@Test
	public void searchByIdTest() {
		accessory = accessoryDao.register(accessory);

		Accessory databaseAccessory = accessoryDao.searchById(Accessory.class, accessory.getId());

		assertNotNull(databaseAccessory);
		assertEquals(accessory.getId(), databaseAccessory.getId());
		assertEquals(accessory.getCode(), databaseAccessory.getCode());
		assertEquals(accessory.getName(), databaseAccessory.getName());
		assertEquals(accessory.getPrice(), databaseAccessory.getPrice());
		assertEquals(accessory.getStockQuantity(), databaseAccessory.getStockQuantity());
	}

	/**
	 * Tests if all registered Accessories can be retrieved.
	 */
	@Test
	public void searchAllTest() {
		accessory = accessoryDao.register(accessory);

		List<Accessory> accessoryList = accessoryDao.searchAll(Accessory.class);

		assertNotNull(accessoryList);
		assertFalse(accessoryList.isEmpty());

		Accessory databaseAccessory = accessoryList.get(0);

		assertNotNull(databaseAccessory);
		assertEquals(accessory.getId(), databaseAccessory.getId());
		assertEquals(accessory.getCode(), databaseAccessory.getCode());
		assertEquals(accessory.getName(), databaseAccessory.getName());
		assertEquals(accessory.getPrice(), databaseAccessory.getPrice());
		assertEquals(accessory.getStockQuantity(), databaseAccessory.getStockQuantity());
	}

	/**
	 * Tests if an Accessory can be updated correctly.
	 */
	@Test
	public void updateTest() {
		accessory = accessoryDao.register(accessory);

		accessory.setCode("ABC321");
		accessory.setName("New Accessory Name");
		accessory.setPrice(2000.00);
		accessory.setStockQuantity(30);

		Accessory updatedAccessory = accessoryDao.update(accessory);

		assertNotNull(updatedAccessory);
		assertEquals(accessory.getId(), updatedAccessory.getId());
		assertEquals(accessory.getCode(), updatedAccessory.getCode());
		assertEquals(accessory.getName(), updatedAccessory.getName());
		assertEquals(accessory.getPrice(), updatedAccessory.getPrice());
		assertEquals(accessory.getStockQuantity(), updatedAccessory.getStockQuantity());

		Accessory databaseAccessory = accessoryDao.searchById(Accessory.class, updatedAccessory.getId());

		assertEquals(accessory.getCode(), databaseAccessory.getCode());
		assertEquals(accessory.getName(), databaseAccessory.getName());
		assertEquals(accessory.getPrice(), databaseAccessory.getPrice());
		assertEquals(accessory.getStockQuantity(), databaseAccessory.getStockQuantity());
	}

	/**
	 * Tests if an Accessory can be deleted and is no longer found in the database.
	 */
	@Test
	public void deleteTest() {
		Accessory temporaryAccessory = new Accessory();
		temporaryAccessory.setCode("ACC321");
		temporaryAccessory.setName("Delete Test Accessory Name");
		temporaryAccessory.setPrice(700.00);
		temporaryAccessory.setStockQuantity(25);

		temporaryAccessory = accessoryDao.register(temporaryAccessory);
		accessoryDao.delete(temporaryAccessory);

		Accessory searchAccessory = accessoryDao.searchById(Accessory.class, temporaryAccessory.getId());
		assertNull(searchAccessory);
	}
}
