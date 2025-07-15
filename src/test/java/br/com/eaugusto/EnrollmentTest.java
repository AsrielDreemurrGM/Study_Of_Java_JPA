package br.com.eaugusto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;

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

	public EnrollmentTest() {
		enrollmentDAO = new EnrollmentDAO();
	}

	@Test
	public void register() {
		Enrollment enrollment = new Enrollment();
		enrollment.setCode("A1");
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("ACTIVE");
		enrollment.setAmount(2000.00);

		enrollmentDAO.register(enrollment);

		assertNotNull(enrollment);
		assertNotNull(enrollment.getId());
	}
}
