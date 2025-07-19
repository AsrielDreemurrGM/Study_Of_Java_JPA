package br.com.eaugusto.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 17, 2025
 */
@Entity
@Table(name = "tb_computer")
public class Computer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "computer_seq")
	@SequenceGenerator(name = "computer_seq", sequenceName = "sq_computer", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "computers", fetch = FetchType.EAGER)
	private List<Student> students;

	public Computer() {
		this.students = new ArrayList<>();
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

	public List<Student> getStudents() {
		return students;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
}
