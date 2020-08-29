package io.github.paulooorg.api.model.entities;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Payment extends PersistentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
	@SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize = 3)
	private Long id;
	
	@Column(name = "payment_date")
    private LocalDate paymentDate;

	@Column(name = "payment_number")
    private Integer paymentNumber;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "value", column = @Column(name = "payment")),
	})
    private Money payment;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "value", column = @Column(name = "principal")),
	})
    private Money principal;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "value", column = @Column(name = "interest")),
	})
    private Money interest;
	
	@ManyToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;

    public Payment() {
    }

    public Payment(LocalDate paymentDate, Money payment, Money principal, Money interest) {
        this.paymentDate = paymentDate;
        this.payment = payment;
        this.principal = principal;
        this.interest = interest;
    }

	@Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    	this.id = id;
    }
    
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public Money getPayment() {
        return payment;
    }

    public void setPayment(Money payment) {
        this.payment = payment;
    }

    public Money getPrincipal() {
        return principal;
    }

    public void setPrincipal(Money principal) {
        this.principal = principal;
    }

    public Money getInterest() {
        return interest;
    }

    public void setInterest(Money interest) {
        this.interest = interest;
    }

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
}
