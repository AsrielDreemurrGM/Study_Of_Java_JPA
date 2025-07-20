package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.AccessoryDAO;
import br.com.eaugusto.dao.BrandDAO;
import br.com.eaugusto.dao.CarDAO;
import br.com.eaugusto.dao.interfaces.IAccessoryDAO;
import br.com.eaugusto.dao.interfaces.IBrandDAO;
import br.com.eaugusto.dao.interfaces.ICarDAO;
import br.com.eaugusto.domain.Accessory;
import br.com.eaugusto.domain.Brand;
import br.com.eaugusto.domain.Car;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 19, 2025
 */
public class CarTest {

	private final ICarDAO carDao;
	private final IBrandDAO brandDao;
	private final IAccessoryDAO accessoryDao;

	private Car car;
	private Brand brand;
	private List<Accessory> accessories;

	public CarTest() {
		carDao = new CarDAO();
		brandDao = new BrandDAO();
		accessoryDao = new AccessoryDAO();
	}

	/**
	 * Initializes Brand, Accessory and Car instances before each test.
	 */
	@BeforeEach
	public void setup() {
		brand = new Brand();
		brand.setCode("BRD123");
		brand.setName("Test Brand");
		brand.setSlogan("Driven by Innovation");
		brand = brandDao.register(brand);

		accessories = new ArrayList<>();

		Accessory accessory1 = new Accessory();
		accessory1.setCode("ACC123");
		accessory1.setName("Air Conditioning");
		accessory1.setPrice(1500.00);
		accessory1.setStockQuantity(50);
		accessoryDao.register(accessory1);
		accessories.add(accessory1);

		Accessory accessory2 = new Accessory();
		accessory2.setCode("ACC321");
		accessory2.setName("GPS");
		accessory2.setPrice(900.00);
		accessory2.setStockQuantity(30);
		accessoryDao.register(accessory2);
		accessories.add(accessory2);

		car = new Car();
		car.setCode("CAR123");
		car.setName("Test Car");
		car.setDescription("Test Car Description");
		car.setPrice(20000.00);
		car.setStockQuantity(10);
		car.setBrand(brand);
		car.setAccessories(accessories);
	}

	/**
	 * Cleans up the Car, Accessory and Brand instances after each test.
	 */
	@AfterEach
	public void cleanup() {
		carDao.delete(car);

		for (Accessory eachAccessory : accessories) {
			accessoryDao.delete(eachAccessory);
		}

		brandDao.delete(brand);
	}

	/**
	 * Tests if a Car can be successfully registered with its relationships.
	 */
	@Test
	public void registerTest() {
		car = carDao.register(car);
		assertNotNull(car);
		assertNotNull(car.getId());
		assertNotNull(car.getBrand());
		assertEquals(2, car.getAccessories().size());
	}

	/**
	 * Tests if a Car can be retrieved with its relationships by ID.
	 */
	@Test
	public void searchByIdTest() {
		car = carDao.register(car);

		Car databaseCar = carDao.searchById(Car.class, car.getId());

		assertNotNull(databaseCar);
		assertEquals(car.getId(), databaseCar.getId());
		assertEquals(car.getCode(), databaseCar.getCode());
		assertEquals(car.getName(), databaseCar.getName());
		assertEquals(car.getDescription(), databaseCar.getDescription());
		assertEquals(car.getPrice(), databaseCar.getPrice());
		assertEquals(car.getStockQuantity(), databaseCar.getStockQuantity());

		assertNotNull(databaseCar.getBrand());
		assertEquals(brand.getId(), databaseCar.getBrand().getId());

		assertNotNull(databaseCar.getAccessories());
		assertEquals(2, databaseCar.getAccessories().size());
	}

	/**
	 * Tests if all Cars and their relationships can be retrieved.
	 */
	@Test
	public void searchAllTest() {
		car = carDao.register(car);

		List<Car> carList = carDao.searchAll(Car.class);

		assertNotNull(carList);
		assertFalse(carList.isEmpty());

		Car databaseCar = carList.get(0);
		assertNotNull(databaseCar);
		assertEquals(car.getId(), databaseCar.getId());

		assertNotNull(databaseCar.getBrand());
		assertEquals(brand.getId(), databaseCar.getBrand().getId());

		assertNotNull(databaseCar.getAccessories());
		assertEquals(2, databaseCar.getAccessories().size());
	}

	/**
	 * Tests if a Car can be updated correctly with its relationships.
	 */
	@Test
	public void updateTest() {
		car = carDao.register(car);

		car.setCode("FGH321");
		car.setName("New Car Name");
		car.setPrice(2000.00);
		car.setStockQuantity(30);

		Car updatedCar = carDao.update(car);

		assertNotNull(updatedCar);
		assertEquals(car.getId(), updatedCar.getId());
		assertEquals(car.getName(), updatedCar.getName());
		assertEquals(car.getCode(), updatedCar.getCode());
		assertEquals(car.getDescription(), updatedCar.getDescription());
		assertEquals(car.getPrice(), updatedCar.getPrice());
		assertEquals(car.getStockQuantity(), updatedCar.getStockQuantity());

		assertNotNull(updatedCar.getBrand());
		assertEquals(brand.getId(), updatedCar.getBrand().getId());

		assertNotNull(updatedCar.getAccessories());
		assertEquals(2, updatedCar.getAccessories().size());
	}

	/**
	 * Tests if a Car and its relationships can be deleted from the database.
	 */
	@Test
	public void deleteTest() {
		Car temporaryCar = new Car();
		temporaryCar.setCode("CAR765");
		temporaryCar.setName("Delete Test Car");
		temporaryCar.setDescription("Delete Description");
		temporaryCar.setPrice(25000.00);
		temporaryCar.setStockQuantity(20);
		temporaryCar.setBrand(brand);
		temporaryCar.setAccessories(accessories);

		temporaryCar = carDao.register(temporaryCar);
		carDao.delete(temporaryCar);

		Car searchCar = carDao.searchById(Car.class, temporaryCar.getId());
		assertNull(searchCar);
	}
}
