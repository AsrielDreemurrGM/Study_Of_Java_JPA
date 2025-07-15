package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

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
	public void register() {
		enrollmentDAO.register(enrollment);

		assertNotNull(enrollment);
		assertNotNull(enrollment.getId());
	}

	@Test
	public void searchById() {
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
	public void deleteEnrollment() {
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
