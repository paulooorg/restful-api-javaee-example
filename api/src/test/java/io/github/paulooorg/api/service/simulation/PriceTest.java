package io.github.paulooorg.api.service.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import io.github.paulooorg.api.dataBuilders.LoanDataBuilder;
import io.github.paulooorg.api.dataBuilders.ModalityDataBuilder;
import io.github.paulooorg.api.model.entities.Loan;

public class PriceTest {
	@Test
	public void shouldSimulateWithPriceStrategyExampleOne() {
		Loan loan = new LoanDataBuilder()
				.amount(new BigDecimal("10000"))
				.firstPaymentDate(LocalDate.now())
				.termInMonths(12)
				.modality(new ModalityDataBuilder().price().monthly().interestRate(new BigDecimal("6")).build())
				.build();
		
		loan.simulate();
		
		assertEquals(12, loan.getPayments().size());
		assertEquals(LocalDate.now(), loan.getPayments().get(0).getPaymentDate());
		assertEquals(loan.getBalance().getRoundedValue(), loan.getAmount().plus(loan.getTotalInterest()).getRoundedValue());
		assertEquals(loan.getAmount().getRoundedValue(), loan.getBalance().minus(loan.getTotalInterest()).getRoundedValue());
	}
	
	@Test
	public void shouldSimulateWithPriceStrategyExampleTwo() {
		Loan loan = new LoanDataBuilder()
				.amount(new BigDecimal("1000"))
				.firstPaymentDate(LocalDate.now())
				.termInMonths(4)
				.modality(new ModalityDataBuilder().price().monthly().interestRate(new BigDecimal("3")).build())
				.build();
		
		loan.simulate();
		
		assertEquals(4, loan.getPayments().size());
		assertEquals(LocalDate.now(), loan.getPayments().get(0).getPaymentDate());
		assertEquals(loan.getBalance().getRoundedValue(), loan.getAmount().plus(loan.getTotalInterest()).getRoundedValue());
		assertEquals(loan.getAmount().getRoundedValue(), loan.getBalance().minus(loan.getTotalInterest()).getRoundedValue());
	}
}
