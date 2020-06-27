package io.github.paulooorg.api.dataBuilders;

import java.math.BigDecimal;

import io.github.paulooorg.api.model.entities.AmortizationMethod;
import io.github.paulooorg.api.model.entities.Modality;
import io.github.paulooorg.api.model.entities.RatePeriod;

public class ModalityDataBuilder {
	private Modality modality;
	
	public ModalityDataBuilder() {
		modality = new Modality();
	}
	
	public ModalityDataBuilder sac() {
		modality.setAmortizationMethod(AmortizationMethod.SAC);
		return this;
	}
	
	public ModalityDataBuilder iterestRate(BigDecimal interestRate) {
		modality.setInterestRate(interestRate);
		return this;
	}
	
	public ModalityDataBuilder monthly() {
		modality.setRatePeriod(RatePeriod.MONTHLY);
		return this;
	}
	
	public ModalityDataBuilder yearly() {
		modality.setRatePeriod(RatePeriod.YEARLY);
		return this;
	}
	
	public Modality build() {
		return modality;
	}
}
