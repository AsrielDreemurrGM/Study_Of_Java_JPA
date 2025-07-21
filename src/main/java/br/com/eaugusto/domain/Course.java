package br.com.eaugusto.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Represents a course entity offered in the system.
 *
 * <p>
 * This class is mapped to the database table {@code tb_course}, and contains
 * identifying information such as code, name, and description. It also
 * maintains a list of student enrollments associated with the course.
 * </p>
 *
 * <p>
 * The relationship with {@link Enrollment} is defined as {@code @OneToMany} and
 * uses cascade operations and orphan removal for automatic management.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 14, 2025
 * 
 * @see javax.persistence.Entity
 * @see javax.persistence.Table
 * @see Enrollment
 */
@Entity
@Table(name = "tb_course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
	@SequenceGenerator(name = "course_seq", sequenceName = "sq_course", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "description", length = 100, nullable = false)
	private String description;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Enrollment> enrollments;

	public Course() {
		this.enrollments = new ArrayList<>();
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

	public String getDescription() {
		return description;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
}
