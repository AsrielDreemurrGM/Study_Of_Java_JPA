package br.com.eaugusto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	public CourseTest() {
		courseDao = new CourseDAO();
	}

	@Test
	public void register() {
		Course course = new Course();
		course.setCode("A1");
		course.setName("Test Course");
		course.setDescription("Java-Backend-Test-Course");

		course = courseDao.register(course);
		assertNotNull(course);
		assertNotNull(course.getId());
	}
}
