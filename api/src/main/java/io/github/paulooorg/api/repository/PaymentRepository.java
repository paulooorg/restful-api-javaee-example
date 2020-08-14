package io.github.paulooorg.api.repository;

import java.util.Optional;

import io.github.paulooorg.api.model.entities.Payment;

public class PaymentRepository extends AbstractEntityRepository<Payment, Long> {
	public PaymentRepository() {
		super(Payment.class);
	}
	
	public Optional<Payment> findPaymentById(Long loanId, Long id) {
		return Optional.ofNullable(em.createQuery("select p from Payment p where p.id = :id and p.loan.id = :loanId", Payment.class)
				.setParameter("loanId", loanId).setParameter("id", id).getSingleResult());
	}
}
