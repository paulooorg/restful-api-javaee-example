package io.github.paulooorg.api.dataBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.model.entities.Modality;
import io.github.paulooorg.api.model.entities.Money;

public class LoanDataBuilder {
	private Loan loan;
	
	public LoanDataBuilder() {
		loan = new Loan();
	}

	public LoanDataBuilder amount(BigDecimal amount) {
		loan.setAmount(new Money(amount));
		return this;
	}
	
	public LoanDataBuilder termInMonths(Integer termInMonths) {
		loan.setTermInMonths(termInMonths);
		return this;
	}
	
	public LoanDataBuilder firstPaymentDate(LocalDate firstPaymentDate) {
		loan.setFirstPaymentDate(firstPaymentDate);
		return this;
	}
	
	public LoanDataBuilder modality(Modality modality) {
		loan.setModality(modality);
		return this;
	}
	
	public Loan build() {
		return loan;
	}
}
