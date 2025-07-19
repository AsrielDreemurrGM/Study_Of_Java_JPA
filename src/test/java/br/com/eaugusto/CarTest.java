package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.CarDAO;
import br.com.eaugusto.dao.interfaces.ICarDAO;
import br.com.eaugusto.domain.Car;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 19, 2025
 */
public class CarTest {

	private final ICarDAO carDao;
	private Car car;

	public CarTest() {
		carDao = new CarDAO();
	}

	/**
	 * Initializes a Car instance before each test.
	 */
	@BeforeEach
	public void createCar() {
		car = new Car();
		car.setCode("CAR123");
		car.setName("Test Car");
		car.setDescription("Test Car Description");
		car.setPrice(20000.00);
		car.setStockQuantity(10);
	}

	/**
	 * Cleans up the Car instance after each test.
	 */
	@AfterEach
	public void cleanupCar() {
		carDao.delete(car);
	}

	/**
	 * Tests if a Car can be successfully registered in the database.
	 */
	@Test
	public void registerTest() {
		car = carDao.register(car);
		assertNotNull(car);
		assertNotNull(car.getId());
	}

	/**
	 * Tests if a Car can be retrieved by its ID.
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
	}

	/**
	 * Tests if all registered Cars can be retrieved.
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
		assertEquals(car.getCode(), databaseCar.getCode());
		assertEquals(car.getName(), databaseCar.getName());
		assertEquals(car.getDescription(), databaseCar.getDescription());
		assertEquals(car.getPrice(), databaseCar.getPrice());
		assertEquals(car.getStockQuantity(), databaseCar.getStockQuantity());
	}

	/**
	 * Tests if a Car can be updated correctly.
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
		assertEquals(car.getCode(), updatedCar.getCode());
		assertEquals(car.getName(), updatedCar.getName());
		assertEquals(car.getDescription(), updatedCar.getDescription());
		assertEquals(car.getPrice(), updatedCar.getPrice());
		assertEquals(car.getStockQuantity(), updatedCar.getStockQuantity());

		Car databaseCar = carDao.searchById(Car.class, updatedCar.getId());

		assertEquals(car.getCode(), databaseCar.getCode());
		assertEquals(car.getName(), databaseCar.getName());
		assertEquals(car.getDescription(), updatedCar.getDescription());
		assertEquals(car.getPrice(), databaseCar.getPrice());
		assertEquals(car.getStockQuantity(), databaseCar.getStockQuantity());
	}

	/**
	 * Tests if a Car can be deleted and is no longer found in the database.
	 */
	@Test
	public void deleteTest() {
		Car temporaryCar = new Car();
		temporaryCar.setCode("CAR765");
		temporaryCar.setName("Delete Test - Car Name");
		temporaryCar.setDescription("Delete Test - Car Description");
		temporaryCar.setPrice(25000.00);
		temporaryCar.setStockQuantity(20);

		temporaryCar = carDao.register(temporaryCar);
		carDao.delete(temporaryCar);

		Car searchCar = carDao.searchById(Car.class, temporaryCar.getId());
		assertNull(searchCar);
	}
}
