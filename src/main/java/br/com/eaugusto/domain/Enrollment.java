package br.com.eaugusto.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Represents an enrollment record of a student in a course or program.
 *
 * <p>
 * This class is mapped to the table {@code tb_enrollment} and holds information
 * such as enrollment date, payment amount, and enrollment status.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 15, 2025
 * 
 * @see javax.persistence.Entity
 * @see javax.persistence.Table
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
}
