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

import br.com.eaugusto.dao.CourseDAO;
import br.com.eaugusto.dao.EnrollmentDAO;
import br.com.eaugusto.dao.ICourseDAO;
import br.com.eaugusto.dao.IEnrollmentDAO;
import br.com.eaugusto.domain.Course;
import br.com.eaugusto.domain.Enrollment;

/**
 * Unit tests for {@link br.com.eaugusto.domain.Enrollment} entity using
 * {@link br.com.eaugusto.dao.EnrollmentDAO}.
 * 
 * <p>
 * This test class ensures that all core CRUD functionalities are working as
 * expected for {@link Enrollment} entities, including handling of date-time
 * attributes and numerical values.
 * </p>
 *
 * <p>
 * Time-based fields are compared using
 * {@link java.time.temporal.ChronoUnit#MILLIS} for accuracy.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class EnrollmentTest {

	private final IEnrollmentDAO enrollmentDAO;
	private final ICourseDAO courseDAO;
	private Enrollment enrollment;
	private Course course;

	public EnrollmentTest() {
		enrollmentDAO = new EnrollmentDAO();
		courseDAO = new CourseDAO();
	}

	/**
	 * Initializes an Enrollment instance before each test.
	 */
	@BeforeEach
	public void createEnrollment() {
		course = createCourse();

		enrollment = new Enrollment();
		enrollment.setCode("A1");
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("ACTIVE");
		enrollment.setAmount(2000.00);
		enrollment.setCourse(course);
	}

	/**
	 * Initializes a Course instance.
	 * 
	 * @param code Course code
	 */
	public Course createCourse() {
		Course newCourse = new Course();
		newCourse.setCode("TM" + System.currentTimeMillis() % 100000);
		newCourse.setName("Enrollment Test Course");
		newCourse.setDescription("Java-ManyToOne-OneToMany-Test-Course");
		return courseDAO.register(newCourse);
	}

	/**
	 * Cleans up the Enrollment and Course instances after each test.
	 */
	@AfterEach
	public void cleanup() {
		List<Enrollment> allEnrollments = enrollmentDAO.searchAll(Enrollment.class);
		for (Enrollment eachEnrollment : allEnrollments) {
			enrollmentDAO.delete(eachEnrollment);
		}

		List<Course> allCourses = courseDAO.searchAll(Course.class);
		for (Course eachCourse : allCourses) {
			courseDAO.delete(eachCourse);
		}
	}

	/**
	 * Tests if an Enrollment can be successfully registered in the database.
	 */
	@Test
	public void registerTest() {
		enrollmentDAO.register(enrollment);

		assertNotNull(enrollment);
		assertNotNull(enrollment.getId());
	}

	/**
	 * Tests if an Enrollment can be retrieved by its ID.
	 */
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

	/**
	 * Tests if all registered Enrollments can be retrieved.
	 */
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

	/**
	 * Tests if an Enrollment can be updated correctly.
	 */
	@Test
	public void updateTest() {
		enrollment = enrollmentDAO.register(enrollment);
		course = createCourse();

		enrollment.setCode("A3");
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("INACTIVE");
		enrollment.setAmount(2500.00);
		enrollment.setCourse(course);

		Enrollment updatedEnrollment = enrollmentDAO.update(enrollment);

		assertNotNull(updatedEnrollment);
		assertEquals(enrollment.getId(), updatedEnrollment.getId());
		assertEquals(enrollment.getCode(), updatedEnrollment.getCode());
		assertEquals(enrollment.getStatus(), updatedEnrollment.getStatus());
		assertEquals(enrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS),
				updatedEnrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS));
		assertEquals(enrollment.getCourse().getId(), updatedEnrollment.getCourse().getId());

		Enrollment databaseEnrollment = enrollmentDAO.searchById(Enrollment.class, updatedEnrollment.getId());

		assertNotNull(databaseEnrollment);
		assertEquals(enrollment.getId(), databaseEnrollment.getId());
		assertEquals(enrollment.getCode(), databaseEnrollment.getCode());
		assertEquals(enrollment.getStatus(), databaseEnrollment.getStatus());
		assertEquals(enrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS),
				databaseEnrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS));
		assertEquals(enrollment.getCourse().getId(), databaseEnrollment.getCourse().getId());
	}

	/**
	 * Tests if an Enrollment can be deleted and is no longer found in the database.
	 */
	@Test
	public void deleteEnrollmentTest() {
		Course temporaryCourse = createCourse();

		Enrollment temporaryEnrollment = new Enrollment();
		temporaryEnrollment.setCode("A2");
		temporaryEnrollment.setEnrollmentDate(Instant.now());
		temporaryEnrollment.setStatus("ACTIVE");
		temporaryEnrollment.setAmount(2500.00);
		temporaryEnrollment.setCourse(temporaryCourse);

		temporaryEnrollment = enrollmentDAO.register(temporaryEnrollment);
		enrollmentDAO.delete(temporaryEnrollment);

		Enrollment result = enrollmentDAO.searchById(Enrollment.class, temporaryEnrollment.getId());
		assertNull(result);

		courseDAO.delete(temporaryCourse);

		Course result2 = courseDAO.searchById(Course.class, temporaryCourse.getId());
		assertNull(result2);
	}
}
