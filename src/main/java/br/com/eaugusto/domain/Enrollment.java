package br.com.eaugusto.domain;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Represents an enrollment record of a student in a course.
 *
 * <p>
 * This class is mapped to the table {@code tb_enrollment} and contains data
 * such as the enrollment date, payment amount, and status. It maintains
 * references to the {@link Course} and {@link Student} entities.
 * </p>
 *
 * <p>
 * The {@code course} association is {@code @ManyToOne}, and the {@code student}
 * association is {@code @OneToOne} with cascading and orphan removal enabled,
 * ensuring the student data is tightly coupled with the enrollment.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 * 
 * @see javax.persistence.Entity
 * @see javax.persistence.Table
 * @see Course
 * @see Student
 */
@Entity
@Table(name = "tb_enrollment")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_seq")
	@SequenceGenerator(name = "enrollment_seq", sequenceName = "sq_enrollment", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;

	@Column(name = "enrollment_date", nullable = false)
	private Instant enrollmentDate;

	@Column(name = "amount", nullable = false)
	private Double amount;

	@Column(name = "status", nullable = false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "id_course_fk", foreignKey = @ForeignKey(name = "fk_course_enrollment"), referencedColumnName = "id", nullable = false)
	private Course course;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_student_fk", foreignKey = @ForeignKey(name = "fk_student_enrollment"), nullable = false)
	private Student student;

	public Student getStudent() {
		return student;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public Instant getEnrollmentDate() {
		return enrollmentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public String getStatus() {
		return status;
	}

	public Course getCourse() {
		return course;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEnrollmentDate(Instant enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
