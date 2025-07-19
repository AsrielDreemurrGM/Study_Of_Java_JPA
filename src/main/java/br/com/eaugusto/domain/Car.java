package br.com.eaugusto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 19, 2025
 */
@Entity
@Table(name = "tb_car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
	@SequenceGenerator(name = "car_seq", sequenceName = "sq_car", initialValue = 1, allocationSize = 1)
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
}
