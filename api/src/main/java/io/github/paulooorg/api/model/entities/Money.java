package io.github.paulooorg.api.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Money {
	@Column(scale = 2)
    private BigDecimal value;

    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;

    public static final Currency CURRENCY = Currency.getInstance("BRL"); 
    
    public Money() {
        this.value = BigDecimal.ZERO;
    }

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money times(BigDecimal factor) {
        return new Money(value.multiply(factor));
    }

    public Money times(Integer factor) {
        return new Money(value.multiply(new BigDecimal(factor)));
    }

    public Money minus(Money value) {
        return new Money(this.value.subtract(value.getValue()));
    }

    public Money plus(Money value) {
        return new Money(this.value.add(value.getValue()));
    }

    public Money div(Integer divisor) {
        return new Money(value.divide(new BigDecimal(divisor)));
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getRoundedValue() {
    	return value.setScale(CURRENCY.getDefaultFractionDigits(), DEFAULT_ROUNDING_MODE);
    }
    
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(CURRENCY.getSymbol()).append(" ");
		toStringBuilder.append(getRoundedValue());
		return toStringBuilder.toString();
	}
}
