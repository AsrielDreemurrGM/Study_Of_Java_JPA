package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.ComputerDAO;
import br.com.eaugusto.dao.CourseDAO;
import br.com.eaugusto.dao.EnrollmentDAO;
import br.com.eaugusto.dao.IComputerDAO;
import br.com.eaugusto.dao.ICourseDAO;
import br.com.eaugusto.dao.IEnrollmentDAO;
import br.com.eaugusto.dao.IStudentDAO;
import br.com.eaugusto.dao.StudentDAO;
import br.com.eaugusto.domain.Computer;
import br.com.eaugusto.domain.Course;
import br.com.eaugusto.domain.Enrollment;
import br.com.eaugusto.domain.Student;

/*
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public class StudentTest {

	private final IStudentDAO studentDao;
	private final IComputerDAO computerDao;
	private final IEnrollmentDAO enrollmentDao;
	private final ICourseDAO courseDAO;
	private Student student;

	public StudentTest() {
		studentDao = new StudentDAO();
		computerDao = new ComputerDAO();
		enrollmentDao = new EnrollmentDAO();
		courseDAO = new CourseDAO();
	}

	/**
	 * Initializes a Student instance before each test.
	 */
	@BeforeEach
	public void setup() {
		List<Computer> computerList = createComputerlist();
		Computer computer3 = createComputer();
		Enrollment enrollment = createEnrollment();

		student = new Student();
		student.setCode("A1");
		student.setName("Test Student");
		student.setComputers(computerList);
		student.addComputer(computer3);
		enrollment.setStudent(student);
		student.setEnrollment(enrollment);
	}

	public List<Computer> createComputerlist() {
		Computer computer1 = new Computer();
		computer1.setCode("TestPC1");
		computer1.setName("Test PC1");

		Computer computer2 = new Computer();
		computer2.setCode("TestPC2");
		computer2.setName("Test PC2");

		List<Computer> list = new ArrayList<>();
		list.add(computer1);
		list.add(computer2);

		return list;
	}

	public Computer createComputer() {
		Computer computer3 = new Computer();
		computer3.setCode("TestPC3");
		computer3.setName("Test PC3");

		return computer3;
	}

	public Enrollment createEnrollment() {
		Course course = new Course();
		course.setCode("TestCours1");
		course.setName("Test Course");
		course.setDescription("Enrollment-Test-Course-Description");
		courseDAO.register(course);

		Enrollment enrollment = new Enrollment();
		enrollment.setCode("TestEnroll");
		enrollment.setEnrollmentDate(Instant.now());
		enrollment.setStatus("ACTIVE");
		enrollment.setAmount(1000.00);
		enrollment.setCourse(course);

		return enrollment;
	}

	/**
	 * Cleans up the Student instance after each test.
	 */
	@AfterEach
	public void cleanup() {
		List<Student> allStudents = studentDao.searchAll(Student.class);
		for (Student eachStudent : allStudents) {
			studentDao.delete(eachStudent);
		}

		List<Computer> allComputers = computerDao.searchAll(Computer.class);
		for (Computer eachComputer : allComputers) {
			computerDao.delete(eachComputer);
		}

		List<Course> allCourses = courseDAO.searchAll(Course.class);
		for (Course eachCourse : allCourses) {
			courseDAO.delete(eachCourse);
		}

		List<Enrollment> allEnrollments = enrollmentDao.searchAll(Enrollment.class);
		for (Enrollment eachEnrollment : allEnrollments) {
			enrollmentDao.delete(eachEnrollment);
		}
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
		assertEquals(student.getEnrollment().getId(), databaseStudent.getEnrollment().getId());
		assertEquals(student.getComputers().get(0).getCode(), databaseStudent.getComputers().get(0).getCode());
		assertEquals(student.getComputers().get(1).getCode(), databaseStudent.getComputers().get(1).getCode());
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
