package io.github.paulooorg.api.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ModalityTest {
	@Test
	public void shouldConvertYearlyInterestRateToMonthlyInterestRate() {
		Modality modality = new Modality();
		modality.setRatePeriod(RatePeriod.YEARLY);
		modality.setInterestRate(new BigDecimal("101.2196"));
		assertEquals(new BigDecimal("6.0000"), modality.getMonthlyInterestRate());
	}
	
	@Test
	public void shouldReturnSameInterestRateWhenMonthlyRatePeriod() {
		Modality modality = new Modality();
		modality.setRatePeriod(RatePeriod.MONTHLY);
		modality.setInterestRate(new BigDecimal("0.47"));
		assertEquals(new BigDecimal("0.47"), modality.getMonthlyInterestRate());
	}
}
