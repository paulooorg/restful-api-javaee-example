package io.github.paulooorg.api.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LoanDTO extends DTO {
	private BigDecimal amount;
	
	private BigDecimal balance;
	
	private BigDecimal totalInterest;
	
	private Integer termInMonths;
	
	private LocalDate firstPaymentDate;
	
	private Long clientId;
	
	private Long modalityId;
	
	private List<PaymentDTO> payments;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getModalityId() {
		return modalityId;
	}

	public void setModalityId(Long modalityId) {
		this.modalityId = modalityId;
	}

	public List<PaymentDTO> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentDTO> payments) {
		this.payments = payments;
	}
}
