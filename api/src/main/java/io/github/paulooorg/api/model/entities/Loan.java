package io.github.paulooorg.api.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Loan extends PersistentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_sequence")
	@SequenceGenerator(name = "loan_sequence", sequenceName = "loan_sequence", allocationSize = 3)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
    private Client client;	

    @Embedded
    private Money amount;

    @Column(name = "term_in_months")
    private Integer termInMonths;

    @Column(name = "first_payment_date")
    private LocalDate firstPaymentDate;

    @ManyToOne
    @JoinColumn(name = "modality_id")
    private Modality modality;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    public void simulate() {
        setPayments(modality.getAmortizationMethod().getAmortizationStrategy().calculate(this));
    }

    public void addPayment(Payment payment) {
    	payment.setLoan(this);
        getPayments().add(payment);
    }

    public Money getBalance() {
    	return getPayments().stream().map(p -> p.getPayment()).reduce(Money::plus).orElse(new Money());
    }

    public Money getTotalInterest() {
    	return getPayments().stream().map(p -> p.getInterest()).reduce(Money::plus).orElse(new Money());
    }

	@Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    	this.id = id;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Integer getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(Integer termInMonths) {
        this.termInMonths = termInMonths;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
