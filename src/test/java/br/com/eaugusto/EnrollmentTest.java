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

import br.com.eaugusto.dao.EnrollmentDAO;
import br.com.eaugusto.dao.IEnrollmentDAO;
import br.com.eaugusto.domain.Enrollment;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class EnrollmentTest {

	private final IEnrollmentDAO enrollmentDAO;
	private Enrollment enrollment;

	public EnrollmentTest() {
		enrollmentDAO = new EnrollmentDAO();
	}

	@BeforeEach
	public void createEnrollment() {
		enrollment = new Enrollment();
		enrollment.setCode("A1");
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("ACTIVE");
		enrollment.setAmount(2000.00);
	}

	@AfterEach
	public void cleanupEnrollment() {
		enrollmentDAO.delete(enrollment);
	}

	@Test
	public void registerTest() {
		enrollmentDAO.register(enrollment);

		assertNotNull(enrollment);
		assertNotNull(enrollment.getId());
	}

	@Test
	public void searchByIdTest() {
		enrollment = enrollmentDAO.register(enrollment);

		Enrollment databaseEnrollment = enrollmentDAO.searchById(Enrollment.class, enrollment.getId());

		assertNotNull(databaseEnrollment);
		assertEquals(enrollment.getId(), databaseEnrollment.getId());
		assertEquals(enrollment.getCode(), databaseEnrollment.getCode());
		assertEquals(enrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS),
				databaseEnrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS));
		assertEquals(enrollment.getAmount(), databaseEnrollment.getAmount());
		assertEquals(enrollment.getStatus(), databaseEnrollment.getStatus());
	}

	@Test
	public void searchAllTest() {
		enrollment = enrollmentDAO.register(enrollment);

		List<Enrollment> enrollmentList = enrollmentDAO.searchAll(Enrollment.class);

		assertNotNull(enrollmentList);
		assertFalse(enrollmentList.isEmpty());

		Enrollment databaseEnrollment = enrollmentList.get(0);

		assertNotNull(databaseEnrollment);
		assertEquals(enrollment.getId(), databaseEnrollment.getId());
		assertEquals(enrollment.getCode(), databaseEnrollment.getCode());
		assertEquals(enrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS),
				databaseEnrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS));
		assertEquals(enrollment.getAmount(), databaseEnrollment.getAmount());
		assertEquals(enrollment.getStatus(), databaseEnrollment.getStatus());
	}

	@Test
	public void updateTest() {
		enrollment = enrollmentDAO.register(enrollment);

		enrollment.setCode("A3");
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("INACTIVE");
		enrollment.setAmount(2500.00);

		Enrollment updatedEnrollment = enrollmentDAO.update(enrollment);

		assertNotNull(updatedEnrollment);
		assertEquals(enrollment.getId(), updatedEnrollment.getId());
		assertEquals(enrollment.getCode(), updatedEnrollment.getCode());
		assertEquals(enrollment.getStatus(), updatedEnrollment.getStatus());
		assertEquals(enrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS),
				updatedEnrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS));

		Enrollment databaseEnrollment = enrollmentDAO.searchById(Enrollment.class, updatedEnrollment.getId());

		assertNotNull(databaseEnrollment);
		assertEquals(enrollment.getId(), databaseEnrollment.getId());
		assertEquals(enrollment.getCode(), databaseEnrollment.getCode());
		assertEquals(enrollment.getStatus(), databaseEnrollment.getStatus());
		assertEquals(enrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS),
				databaseEnrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS));
	}

	@Test
	public void deleteEnrollmentTest() {
		Enrollment temporaryEnrollment = new Enrollment();
		temporaryEnrollment.setCode("A2");
		temporaryEnrollment.setEnrollmentDate(Instant.now());
		temporaryEnrollment.setStatus("ACTIVE");
		temporaryEnrollment.setAmount(2500.00);

		temporaryEnrollment = enrollmentDAO.register(temporaryEnrollment);
		enrollmentDAO.delete(temporaryEnrollment);

		Enrollment result = enrollmentDAO.searchById(Enrollment.class, temporaryEnrollment.getId());
		assertNull(result);
	}
}
