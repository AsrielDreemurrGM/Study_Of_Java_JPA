package br.com.eaugusto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.CourseDAO;
import br.com.eaugusto.dao.EnrollmentDAO;
import br.com.eaugusto.dao.StudentDAO;
import br.com.eaugusto.dao.interfaces.ICourseDAO;
import br.com.eaugusto.dao.interfaces.IEnrollmentDAO;
import br.com.eaugusto.dao.interfaces.IStudentDAO;
import br.com.eaugusto.domain.Course;
import br.com.eaugusto.domain.Enrollment;
import br.com.eaugusto.domain.Student;

/**
 * Unit tests for {@link Enrollment} entity using {@link EnrollmentDAO}.
 * 
 * <p>
 * This test class ensures correct CRUD functionality for {@link Enrollment}
 * entities, including validation of date-time and numeric fields with
 * millisecond precision.
 * </p>
 * 
 * <p>
 * Tests also verify the integrity of relationships between {@link Enrollment},
 * {@link Course}, and {@link Student} entities.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class EnrollmentTest {

	private final IEnrollmentDAO enrollmentDAO;
	private final ICourseDAO courseDAO;
	private final IStudentDAO studentDao;
	private Enrollment enrollment;
	private Course course;
	private Student student;

	public EnrollmentTest() {
		enrollmentDAO = new EnrollmentDAO();
		courseDAO = new CourseDAO();
		studentDao = new StudentDAO();
	}

	/**
	 * Initializes an Enrollment instance before each test.
	 */
	@BeforeEach
	public void createEnrollment() {
		course = createCourse();
		student = createStudent();

		enrollment = new Enrollment();
		enrollment.setCode("A1");
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("ACTIVE");
		enrollment.setAmount(2000.00);
		enrollment.setCourse(course);
		enrollment.setStudent(student);
		student.setEnrollment(enrollment);
	}

	/**
	 * Initializes a Course instance.
	 */
	public Course createCourse() {
		Course newCourse = new Course();
		newCourse.setCode("TM" + System.currentTimeMillis() % 100_000_000);
		newCourse.setName("Enrollment Test Course");
		newCourse.setDescription("Java-ManyToOne-OneToMany-Test-Course");
		return courseDAO.register(newCourse);
	}

	/**
	 * Initializes a Student instance.
	 */
	public Student createStudent() {
		Student newStudent = new Student();
		newStudent.setCode("TIME" + System.currentTimeMillis() % 10000);
		newStudent.setName("Enrollment Test Course");
		return newStudent;
	}

	/**
	 * Cleans up the Enrollment, Course and Student instances after each test.
	 */
	@AfterEach
	public void cleanup() {

		List<Student> allStudents = studentDao.searchAll(Student.class);
		for (Student eachStudent : allStudents) {
			studentDao.delete(eachStudent);
		}

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
		assertEquals(enrollment.getCourse().getId(), databaseEnrollment.getCourse().getId());
		assertEquals(enrollment.getStudent().getId(), databaseEnrollment.getStudent().getId());
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
		assertEquals(enrollment.getCourse().getId(), databaseEnrollment.getCourse().getId());
		assertEquals(enrollment.getStudent().getId(), databaseEnrollment.getStudent().getId());
	}

	/**
	 * Tests if an Enrollment can be updated correctly.
	 */
	@Test
	public void updateTest() {
		enrollment = enrollmentDAO.register(enrollment);

		enrollment.setCode("A3");
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("INACTIVE");
		enrollment.setAmount(2500.00);
		enrollment.setCourse(course);
		enrollment.setStudent(student);

		Enrollment updatedEnrollment = enrollmentDAO.update(enrollment);

		assertNotNull(updatedEnrollment);
		assertEquals(enrollment.getId(), updatedEnrollment.getId());
		assertEquals(enrollment.getCode(), updatedEnrollment.getCode());
		assertEquals(enrollment.getStatus(), updatedEnrollment.getStatus());
		assertEquals(enrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS),
				updatedEnrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS));
		assertEquals(enrollment.getCourse().getId(), updatedEnrollment.getCourse().getId());
		assertEquals(enrollment.getStudent().getId(), updatedEnrollment.getStudent().getId());

		Enrollment databaseEnrollment = enrollmentDAO.searchById(Enrollment.class, updatedEnrollment.getId());

		assertNotNull(databaseEnrollment);
		assertEquals(enrollment.getId(), databaseEnrollment.getId());
		assertEquals(enrollment.getCode(), databaseEnrollment.getCode());
		assertEquals(enrollment.getStatus(), databaseEnrollment.getStatus());
		assertEquals(enrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS),
				databaseEnrollment.getEnrollmentDate().truncatedTo(ChronoUnit.MILLIS));
		assertEquals(enrollment.getCourse().getId(), databaseEnrollment.getCourse().getId());
		assertEquals(enrollment.getStudent().getId(), databaseEnrollment.getStudent().getId());
	}

	/**
	 * Tests if an Enrollment can be deleted and is no longer found in the database.
	 */
	@Test
	public void deleteEnrollmentTest() {
		Course temporaryCourse = createCourse();
		Student temporaryStudent = createStudent();

		Enrollment temporaryEnrollment = new Enrollment();
		temporaryEnrollment.setCode("A2");
		temporaryEnrollment.setEnrollmentDate(Instant.now());
		temporaryEnrollment.setStatus("ACTIVE");
		temporaryEnrollment.setAmount(2500.00);
		temporaryEnrollment.setCourse(temporaryCourse);
		temporaryEnrollment.setStudent(temporaryStudent);

		temporaryEnrollment = enrollmentDAO.register(temporaryEnrollment);

		temporaryStudent.setEnrollment(null);
		studentDao.update(temporaryStudent);

		enrollmentDAO.delete(temporaryEnrollment);

		Enrollment enrollmentSearch = enrollmentDAO.searchById(Enrollment.class, temporaryEnrollment.getId());
		assertNull(enrollmentSearch);

		courseDAO.delete(temporaryCourse);
		Course courseSearch = courseDAO.searchById(Course.class, temporaryCourse.getId());
		assertNull(courseSearch);

		studentDao.delete(temporaryStudent);
		Student studentSearch = studentDao.searchById(Student.class, temporaryStudent.getId());
		assertNull(studentSearch);
	}
}
