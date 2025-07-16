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
 * Represents a product entity with details such as code, name, price, and stock
 * information.
 *
 * <p>
 * This class is mapped to the database table {@code tb_product} and is managed
 * by JPA using {@link javax.persistence.EntityManager}.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 16, 2025
 * 
 * @see javax.persistence.Entity
 * @see javax.persistence.Table
 */
@Entity
@Table(name = "tb_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@SequenceGenerator(name = "product_seq", sequenceName = "sq_product", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "description", length = 100, nullable = false)
	private String description;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "stock_quantity", nullable = false)
	private Integer stockQuantity;

	@Column(name = "creation_date", nullable = false)
	private Instant creationDate;

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

	public Double getPrice() {
		return price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public Instant getCreationDate() {
		return creationDate;
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

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}
}
