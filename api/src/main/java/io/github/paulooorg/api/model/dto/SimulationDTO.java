package io.github.paulooorg.api.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SimulationDTO {
	@NotNull
	@Min(1)
	private Long clientId;
	
	@NotNull
	private BigDecimal amount;

	@NotNull
	private Integer termInMonths;
	
	@NotNull
	private LocalDate firstPaymentDate;
	
	@NotNull
	@Min(1)
	private Long modalityId;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getTermInMonths() {
		return termInMonths;
	}

	public void setTermInMonths(Integer termInMonths) {
		this.termInMonths = termInMonths;
	}

	public LocalDate getFirstPaymentDate() {
		return firstPaymentDate;
	}

	public void setFirstPaymentDate(LocalDate firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}

	public Long getModalityId() {
		return modalityId;
	}

	public void setModalityId(Long modalityId) {
		this.modalityId = modalityId;
	}
}
