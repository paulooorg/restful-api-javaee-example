package io.github.paulooorg.api.repository;

import io.github.paulooorg.api.model.entities.Loan;

public class LoanRepository extends AbstractEntityRepository<Loan, Long> {
	public LoanRepository() {
		super(Loan.class);
	}
}
