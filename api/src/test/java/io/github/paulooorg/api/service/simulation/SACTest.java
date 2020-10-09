package io.github.paulooorg.api.service.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import io.github.paulooorg.api.dataBuilders.LoanDataBuilder;
import io.github.paulooorg.api.dataBuilders.ModalityDataBuilder;
import io.github.paulooorg.api.model.entities.Loan;

public class SACTest {
	@Test
	public void shouldSimulateWithSACStrategyExampleOne() {
		Loan loan = new LoanDataBuilder()
				.amount(new BigDecimal("12300"))
				.firstPaymentDate(LocalDate.now())
				.termInMonths(60)
				.modality(new ModalityDataBuilder().sac().monthly().interestRate(new BigDecimal("0.48")).build())
				.build();
		
		loan.simulate();
		
		assertEquals(60, loan.getPayments().size());
		assertEquals(LocalDate.now(), loan.getPayments().get(0).getPaymentDate());
		assertEquals(loan.getBalance().getRoundedValue(), loan.getAmount().plus(loan.getTotalInterest()).getRoundedValue());
		assertEquals(loan.getAmount().getRoundedValue(), loan.getBalance().minus(loan.getTotalInterest()).getRoundedValue());
	}
}
