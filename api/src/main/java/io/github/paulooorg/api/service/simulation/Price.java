package io.github.paulooorg.api.service.simulation;

import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.model.entities.Payment;

import java.util.Collections;
import java.util.List;

public class Price implements AmortizationStrategy {
    @Override
    public List<Payment> calculate(Loan loan) {
        return Collections.emptyList();
    }
}
