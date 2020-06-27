package io.github.paulooorg.api.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Modality extends PersistentEntity {
    private String name;

    private String description;

    @Column(name = "amortization_method")
    @Enumerated(EnumType.STRING)
    private AmortizationMethod amortizationMethod;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "rate_period")
    @Enumerated(EnumType.STRING)
    private RatePeriod ratePeriod;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AmortizationMethod getAmortizationMethod() {
        return amortizationMethod;
    }

    public void setAmortizationMethod(AmortizationMethod amortizationMethod) {
        this.amortizationMethod = amortizationMethod;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public RatePeriod getRatePeriod() {
        return ratePeriod;
    }

    public void setRatePeriod(RatePeriod ratePeriod) {
        this.ratePeriod = ratePeriod;
    }
}
