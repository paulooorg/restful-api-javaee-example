package io.github.paulooorg.api.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Modality extends PersistentEntity {
	public static final int INTEREST_RATE_SCALE = 4;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modality_sequence")
	@SequenceGenerator(name = "modality_sequence", sequenceName = "modality_sequence", allocationSize = 3)
	private Long id;
	
	@Column(length = 150)
	@NotNull
	@NotBlank
    private String name;

	@Column(length = 150)
	@NotNull
	@NotBlank
    private String description;

    @Column(name = "amortization_method")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AmortizationMethod amortizationMethod;

    @Column(name = "interest_rate", scale = INTEREST_RATE_SCALE)
    @NotNull
    private BigDecimal interestRate;

    @Column(name = "rate_period")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RatePeriod ratePeriod;

    public BigDecimal getMonthlyInterestRate() {
    	if (ratePeriod.equals(RatePeriod.YEARLY)) {
    		return new BigDecimal((Math.pow(1 + (interestRate.doubleValue() / 100), 1.0 / 12.0) - 1) * 100).setScale(INTEREST_RATE_SCALE, RoundingMode.HALF_UP);
    	}
    	return interestRate;
    }
    
	@Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    	this.id = id;
    }
    
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
