package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
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
 * Unit tests for {@link br.com.eaugusto.domain.Course} entity using
 * {@link br.com.eaugusto.dao.CourseDAO}.
 *
 * <p>
 * This test class ensures that all core CRUD functionalities are working as
 * expected for {@link Course} entities.
 * </p>
 *
 * <p>
 * Each test ensures correct persistence behavior through the generic DAO
 * structure.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class CourseTest {

	private final ICourseDAO courseDao;
	private final IStudentDAO studentDao;
	private final IEnrollmentDAO enrollmentDao;
	private Course course;

	public CourseTest() {
		courseDao = new CourseDAO();
		studentDao = new StudentDAO();
		enrollmentDao = new EnrollmentDAO();
	}

	/**
	 * Initializes a Course instance before each test.
	 */
	@BeforeEach
	public void createCourse() {
		course = new Course();
		course.setCode("A1");
		course.setName("Test Course");
		course.setDescription("Java-Backend-Test-Course");
	}

	/**
	 * Cleans up the Enrollment, Student And Course instances after each test.
	 */
	@AfterEach
	public void cleanup() {
		List<Student> allStudents = studentDao.searchAll(Student.class);
		for (Student eachStudent : allStudents) {
			studentDao.delete(eachStudent);
		}

		List<Enrollment> allEnrollments = enrollmentDao.searchAll(Enrollment.class);
		for (Enrollment eachEnrollment : allEnrollments) {
			enrollmentDao.delete(eachEnrollment);
		}

		List<Course> allCourses = courseDao.searchAll(Course.class);
		for (Course eachCourse : allCourses) {
			courseDao.delete(eachCourse);
		}
	}

	/**
	 * Tests if a Course can be successfully registered in the database.
	 */
	@Test
	public void registerTest() {
		course = courseDao.register(course);
		assertNotNull(course);
		assertNotNull(course.getId());
	}

	/**
	 * Tests if a Course can be retrieved by its ID.
	 */
	@Test
	public void searchByIdTest() {
		course = courseDao.register(course);

		Course databaseCourse = courseDao.searchById(Course.class, course.getId());

		assertNotNull(databaseCourse);
		assertEquals(course.getId(), databaseCourse.getId());
		assertEquals(course.getCode(), databaseCourse.getCode());
		assertEquals(course.getName(), databaseCourse.getName());
		assertEquals(course.getDescription(), databaseCourse.getDescription());
	}

	/**
	 * Tests if all registered Courses can be retrieved.
	 */
	@Test
	public void searchAllTest() {
		course = courseDao.register(course);

		List<Course> courseList = courseDao.searchAll(Course.class);

		assertNotNull(courseList);
		assertFalse(courseList.isEmpty());

		Course databaseCourse = courseList.get(0);

		assertNotNull(databaseCourse);
		assertEquals(course.getId(), databaseCourse.getId());
		assertEquals(course.getCode(), databaseCourse.getCode());
		assertEquals(course.getName(), databaseCourse.getName());
		assertEquals(course.getDescription(), databaseCourse.getDescription());
	}

	/**
	 * Tests if a Course can be updated correctly.
	 */
	@Test
	public void updateTest() {
		course = courseDao.register(course);

		course.setCode("A3");
		course.setName("New Information");
		course.setDescription("Temporary Update Course");

		Course updatedCourse = courseDao.update(course);

		assertNotNull(updatedCourse);
		assertEquals(course.getId(), updatedCourse.getId());
		assertEquals(course.getCode(), updatedCourse.getCode());
		assertEquals(course.getName(), updatedCourse.getName());
		assertEquals(course.getDescription(), updatedCourse.getDescription());

		Course databaseCourse = courseDao.searchById(Course.class, updatedCourse.getId());

		assertEquals(course.getCode(), databaseCourse.getCode());
		assertEquals(course.getName(), databaseCourse.getName());
		assertEquals(course.getDescription(), databaseCourse.getDescription());
	}

	/**
	 * Tests if a Course can be deleted and is no longer found in the database.
	 */
	@Test
	public void deleteTest() {
		Course temporaryCourse = new Course();
		temporaryCourse.setCode("A2");
		temporaryCourse.setName("To Delete");
		temporaryCourse.setDescription("Temp Course");

		temporaryCourse = courseDao.register(temporaryCourse);
		courseDao.delete(temporaryCourse);

		Course result = courseDao.searchById(Course.class, temporaryCourse.getId());
		assertNull(result);
	}

	@Test
	public void enrollmentRelationshipTest() {
		Course registeredCourse = courseDao.register(course);

		Student student = new Student();
		student.setCode("S123456");
		student.setName("Enrollment Test Student");

		Enrollment enrollment = new Enrollment();
		enrollment.setCode("EN123456");
		enrollment.setAmount(1500.0);
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("ACTIVE");
		enrollment.setStudent(student);
		enrollment.setCourse(registeredCourse);

		enrollmentDao.register(enrollment);

		registeredCourse.setEnrollments(List.of(enrollment));
		courseDao.update(registeredCourse);

		assertNotNull(registeredCourse.getEnrollments());
		assertFalse(registeredCourse.getEnrollments().isEmpty());
		assertEquals("EN123456", registeredCourse.getEnrollments().get(0).getCode());
	}
}
