package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.CourseDAO;
import br.com.eaugusto.dao.ICourseDAO;
import br.com.eaugusto.domain.Course;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 */
public class CourseTest {

	private final ICourseDAO courseDao;
	private Course course;

	public CourseTest() {
		courseDao = new CourseDAO();
	}

	@BeforeEach
	public void createCourse() {
		course = new Course();
		course.setCode("A1");
		course.setName("Test Course");
		course.setDescription("Java-Backend-Test-Course");
	}

	@AfterEach
	public void cleanupCourse() {
		courseDao.delete(course);
	}

	@Test
	public void registerTest() {
		course = courseDao.register(course);
		assertNotNull(course);
		assertNotNull(course.getId());
	}

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

	@Test
	public void searchAllTest() {
		course = courseDao.register(course);

		List<Course> courseList = courseDao.searchAll(Course.class);

		assertNotNull(courseList);

		Course databaseCourse = courseList.get(0);
		assertNotNull(databaseCourse);
		assertEquals(course.getId(), databaseCourse.getId());
		assertEquals(course.getCode(), databaseCourse.getCode());
		assertEquals(course.getName(), databaseCourse.getName());
		assertEquals(course.getDescription(), databaseCourse.getDescription());
	}

	@Test
	public void deleteCourseTest() {
		Course temporaryCourse = new Course();
		temporaryCourse.setCode("A2");
		temporaryCourse.setName("To Delete");
		temporaryCourse.setDescription("Temp Course");

		temporaryCourse = courseDao.register(temporaryCourse);
		courseDao.delete(temporaryCourse);

		Course result = courseDao.searchById(Course.class, temporaryCourse.getId());
		assertNull(result);
	}

}
