package io.github.paulooorg.api.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;

@Embeddable
public class Money {
    private BigDecimal value;

    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    //TODO: Currency
    
    public Money() {
        this.value = BigDecimal.ZERO.setScale(2, DEFAULT_ROUNDING_MODE);
    }

    public Money(BigDecimal value) {
        this.value = value.setScale(2, DEFAULT_ROUNDING_MODE);
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
        return new Money(value.divide(new BigDecimal(divisor), DEFAULT_ROUNDING_MODE));
    }

    public BigDecimal getValue() {
        return value;
    }

	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		//TODO: Currency
		toStringBuilder.append("R$ ");
		toStringBuilder.append(value);
		return toStringBuilder.toString();
	}
}
