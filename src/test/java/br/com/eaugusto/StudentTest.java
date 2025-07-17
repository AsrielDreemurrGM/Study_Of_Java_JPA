package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.IStudentDAO;
import br.com.eaugusto.dao.StudentDAO;
import br.com.eaugusto.domain.Student;

/*
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public class StudentTest {

	private final IStudentDAO studentDao;
	private Student student;

	public StudentTest() {
		studentDao = new StudentDAO();
	}

	/**
	 * Initializes a Student instance before each test.
	 */
	@BeforeEach
	public void createStudent() {
		student = new Student();
		student.setCode("A1");
		student.setName("Test Student");
	}

	/**
	 * Cleans up the Student instance after each test.
	 */
	@AfterEach
	public void cleanupStudent() {
		studentDao.delete(student);
	}

	/**
	 * Tests if a Student can be successfully registered in the database.
	 */
	@Test
	public void registerTest() {
		student = studentDao.register(student);
		assertNotNull(student);
		assertNotNull(student.getId());
	}

	/**
	 * Tests if a Student can be retrieved by its ID.
	 */
	@Test
	public void searchByIdTest() {
		student = studentDao.register(student);

		Student databaseStudent = studentDao.searchById(Student.class, student.getId());

		assertNotNull(databaseStudent);
		assertEquals(student.getId(), databaseStudent.getId());
		assertEquals(student.getCode(), databaseStudent.getCode());
		assertEquals(student.getName(), databaseStudent.getName());
		assertEquals(student.getEnrollment(), databaseStudent.getEnrollment());
	}

	/**
	 * Tests if all registered Students can be retrieved.
	 */
	@Test
	public void searchAllTest() {
		student = studentDao.register(student);

		List<Student> studentList = studentDao.searchAll(Student.class);

		assertNotNull(studentList);
		assertFalse(studentList.isEmpty());

		Student databaseStudent = studentList.get(0);

		assertNotNull(databaseStudent);
		assertEquals(student.getId(), databaseStudent.getId());
		assertEquals(student.getCode(), databaseStudent.getCode());
		assertEquals(student.getName(), databaseStudent.getName());
	}

	/**
	 * Tests if a Student can be updated correctly.
	 */
	@Test
	public void updateTest() {
		student = studentDao.register(student);

		student.setCode("A3");
		student.setName("New Information");

		Student updatedStudent = studentDao.update(student);

		assertNotNull(updatedStudent);
		assertEquals(student.getId(), updatedStudent.getId());
		assertEquals(student.getCode(), updatedStudent.getCode());
		assertEquals(student.getName(), updatedStudent.getName());

		Student databaseStudent = studentDao.searchById(Student.class, updatedStudent.getId());

		assertEquals(student.getCode(), databaseStudent.getCode());
		assertEquals(student.getName(), databaseStudent.getName());
	}

	/**
	 * Tests if a Course can be deleted and is no longer found in the database.
	 */
	@Test
	public void deleteTest() {
		Student temporaryStudent = new Student();
		temporaryStudent.setCode("A2");
		temporaryStudent.setName("Student To Delete");

		temporaryStudent = studentDao.register(temporaryStudent);

		studentDao.delete(temporaryStudent);
		Student searchStudent = studentDao.searchById(Student.class, temporaryStudent.getId());
		assertNull(searchStudent);
	}
}
