package io.github.paulooorg.api.service.simulation;

import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.model.entities.Payment;

import java.util.List;

public interface AmortizationStrategy {
    List<Payment> calculate(Loan loan);
}
