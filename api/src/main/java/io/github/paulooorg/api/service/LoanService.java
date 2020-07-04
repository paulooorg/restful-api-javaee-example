package io.github.paulooorg.api.service;

import javax.inject.Inject;

import io.github.paulooorg.api.model.dto.LoanDTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.dto.mapper.LoanMapper;
import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.repository.EntityRepository;
import io.github.paulooorg.api.repository.LoanRepository;

public class LoanService extends AbstractEntityService<LoanDTO, Loan> {
	@Inject
	private LoanRepository loanRepository;
	
	@Override
	public EntityRepository<Loan, Long> getRepository() {
		return loanRepository;
	}

	@Override
	public EntityMapper<LoanDTO, Loan> getMapper() {
		return LoanMapper.INSTANCE;
	}
}
