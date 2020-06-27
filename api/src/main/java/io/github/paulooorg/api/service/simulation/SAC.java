package io.github.paulooorg.api.service.simulation;

import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.model.entities.Money;
import io.github.paulooorg.api.model.entities.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SAC implements AmortizationStrategy {
    @Override
    public List<Payment> calculate(Loan loan) {
        Money amortization = loan.getAmount().div(loan.getTermInMonths());
        List<Payment> payments = new ArrayList<>();
        LocalDate lastPaymentDate = loan.getFirstPaymentDate();
        for (int paymentNumber = 1; paymentNumber <= loan.getTermInMonths(); paymentNumber++) {
            Payment payment = new Payment();
            payment.setPrincipal(amortization);
            Money balance = loan.getAmount().minus(amortization.times(paymentNumber - 1));
            //TODO: RatePeriod
            payment.setInterest(balance.times(loan.getModality().getInterestRate()).div(100));
            payment.setPayment(payment.getPrincipal().plus(payment.getInterest()));
            if (paymentNumber > 1) {
            	//TODO: Payment Interval
            	lastPaymentDate = lastPaymentDate.plusMonths(1);
            } 
            payment.setPaymentDate(lastPaymentDate);
            payment.setPaymentNumber(paymentNumber);
            payments.add(payment);
        }
        return payments;
    }
}
