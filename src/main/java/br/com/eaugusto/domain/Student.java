package br.com.eaugusto.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Represents a student enrolled in a course.
 *
 * <p>
 * This class is mapped to the table {@code tb_student} and includes personal
 * data, one-to-one enrollment information, and a many-to-many relationship with
 * computers.
 * </p>
 *
 * <p>
 * The {@link Enrollment} is tightly coupled with the student via a
 * {@code @OneToOne} relationship. The {@link Computer} association uses a
 * {@code @ManyToMany} relationship and is eagerly fetched.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 * 
 * @see javax.persistence.Entity
 * @see javax.persistence.Table
 */
@Entity
@Table(name = "tb_student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
	@SequenceGenerator(name = "student_seq", sequenceName = "sq_student", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private Enrollment enrollment;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "tb_student_computer", joinColumns = {
			@JoinColumn(name = "id_student_fk") }, inverseJoinColumns = { @JoinColumn(name = "id_computer_fk") })
	private List<Computer> computers;

	public Student() {
		this.computers = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}

	public void addComputer(Computer computer) {
		this.computers.add(computer);
	}
}
