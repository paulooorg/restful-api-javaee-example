package io.github.paulooorg.api.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.github.paulooorg.api.model.entities.AmortizationMethod;
import io.github.paulooorg.api.model.entities.RatePeriod;

public class ModalityDTO implements DTO {
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotNull
	private AmortizationMethod amortizationMethod;
	
	@NotNull
	private BigDecimal interestRate;
	
	@NotNull
	private RatePeriod ratePeriod;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AmortizationMethod getAmortizationMethod() {
		return amortizationMethod;
	}

	public void setAmortizationMethod(AmortizationMethod amortizationMethod) {
		this.amortizationMethod = amortizationMethod;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public RatePeriod getRatePeriod() {
		return ratePeriod;
	}

	public void setRatePeriod(RatePeriod ratePeriod) {
		this.ratePeriod = ratePeriod;
	}
}
