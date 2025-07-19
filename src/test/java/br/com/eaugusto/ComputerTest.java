package br.com.eaugusto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.eaugusto.dao.ComputerDAO;
import br.com.eaugusto.dao.IComputerDAO;
import br.com.eaugusto.dao.IStudentDAO;
import br.com.eaugusto.dao.StudentDAO;
import br.com.eaugusto.domain.Computer;
import br.com.eaugusto.domain.Student;

/**
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
public class ComputerTest {

	private final IComputerDAO computerDao;
	private final IStudentDAO studentDao;
	private Computer computer;
	private Student student;

	public ComputerTest() {
		computerDao = new ComputerDAO();
		studentDao = new StudentDAO();
	}

	@BeforeEach
	public void setup() {
		computer = new Computer();
		computer.setCode("PC" + System.currentTimeMillis() % 100000);
		computer.setName("Test Computer");

		student = new Student();
		student.setCode("ST" + System.currentTimeMillis() % 100000);
		student.setName("Test Student");
	}

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
	}

	@Test
	public void registerTest() {
		computer = computerDao.register(computer);
		assertNotNull(computer);
		assertNotNull(computer.getId());
	}

	@Test
	public void searchByIdTest() {
		computer = computerDao.register(computer);

		Computer dbComputer = computerDao.searchById(Computer.class, computer.getId());

		assertNotNull(dbComputer);
		assertEquals(computer.getId(), dbComputer.getId());
		assertEquals(computer.getCode(), dbComputer.getCode());
		assertEquals(computer.getName(), dbComputer.getName());
	}

	@Test
	public void searchAllTest() {
		computer = computerDao.register(computer);

		List<Computer> computers = computerDao.searchAll(Computer.class);

		assertNotNull(computers);
		assertFalse(computers.isEmpty());

		Computer dbComputer = computers.get(0);
		assertNotNull(dbComputer);
		assertEquals(computer.getId(), dbComputer.getId());
		assertEquals(computer.getCode(), dbComputer.getCode());
		assertEquals(computer.getName(), dbComputer.getName());
	}

	@Test
	public void updateTest() {
		computer = computerDao.register(computer);

		computer.setCode("PC-UPDATED");
		computer.setName("Updated Computer Name");

		Computer updated = computerDao.update(computer);

		assertNotNull(updated);
		assertEquals(computer.getId(), updated.getId());
		assertEquals(computer.getCode(), updated.getCode());
		assertEquals(computer.getName(), updated.getName());

		Computer dbComputer = computerDao.searchById(Computer.class, updated.getId());
		assertEquals(computer.getCode(), dbComputer.getCode());
		assertEquals(computer.getName(), dbComputer.getName());
	}

	@Test
	public void deleteTest() {
		computer = computerDao.register(computer);
		computerDao.delete(computer);

		Computer dbComputer = computerDao.searchById(Computer.class, computer.getId());
		assertNull(dbComputer);
	}

	@Test
	public void manyToManyWithStudentTest() {
		student = studentDao.register(student);
		computer = computerDao.register(computer);

		computer.addStudent(student);
		List<Student> studentList = List.of(student);
		computer.setStudents(studentList);
		student.addComputer(computer);
		computer.setStudents(computer.getStudents());

		computerDao.update(computer);
		studentDao.update(student);

		Computer dbComputer = computerDao.searchById(Computer.class, computer.getId());
		Student dbStudent = studentDao.searchById(Student.class, student.getId());

		assertNotNull(dbComputer);
		assertNotNull(dbStudent);

		assertFalse(dbComputer.getStudents().isEmpty());
		assertEquals(student.getId(), dbComputer.getStudents().get(0).getId());

		assertFalse(dbStudent.getComputers().isEmpty());
		assertEquals(computer.getId(), dbStudent.getComputers().get(0).getId());
	}
}
