package io.github.paulooorg.api.model.entities;

import io.github.paulooorg.api.service.simulation.AmortizationStrategy;
import io.github.paulooorg.api.service.simulation.Price;
import io.github.paulooorg.api.service.simulation.SAC;

public enum AmortizationMethod {
    PRICE(new Price()), SAC(new SAC());

    private AmortizationStrategy amortizationStrategy;

    private AmortizationMethod(AmortizationStrategy amortizationStrategy) {
        this.amortizationStrategy = amortizationStrategy;
    }

    public AmortizationStrategy getAmortizationStrategy() {
        return amortizationStrategy;
    }
}
