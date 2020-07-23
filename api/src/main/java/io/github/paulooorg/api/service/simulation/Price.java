package io.github.paulooorg.api.service.simulation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.model.entities.Modality;
import io.github.paulooorg.api.model.entities.Money;
import io.github.paulooorg.api.model.entities.Payment;

public class Price implements AmortizationStrategy {
    @Override
    public List<Payment> calculate(Loan loan) {
        List<Payment> payments = new ArrayList<>();
        LocalDate lastPaymentDate = loan.getFirstPaymentDate();
        Money currentBalance = loan.getAmount();
        BigDecimal interestRate = calculateInterestRate(loan.getModality());
        BigDecimal factor = calculateFactor(loan, interestRate);
        for (int paymentNumber = 1; paymentNumber <= loan.getTermInMonths(); paymentNumber++) {
        	Payment payment = new Payment();
            payment.setPayment(loan.getAmount().times(factor));
            payment.setInterest(currentBalance.times(interestRate));
            payment.setPrincipal(payment.getPayment().minus(payment.getInterest()));
            if (paymentNumber > 1) {
            	lastPaymentDate = lastPaymentDate.plusMonths(1);
            }
            payment.setPaymentDate(lastPaymentDate);
            payment.setPaymentNumber(paymentNumber);
            payment.setLoan(loan);
            payments.add(payment);
            currentBalance = currentBalance.minus(payment.getPrincipal());
        }
        return payments;
    }
    
    private BigDecimal calculateInterestRate(Modality modality) {
    	return modality.getMonthlyInterestRate().divide(new BigDecimal("100"), 8, RoundingMode.FLOOR);
    }
    
    private BigDecimal calculateFactor(Loan loan, BigDecimal interestRate) {
    	return interestRate.divide(
        		BigDecimal.ONE.subtract(
        				new BigDecimal(Math.pow(BigDecimal.ONE.add(interestRate).doubleValue(), -loan.getTermInMonths()))), 8, RoundingMode.FLOOR);
    }
}
