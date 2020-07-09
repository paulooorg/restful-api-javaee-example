package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import io.github.paulooorg.api.model.dto.LoanDTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.dto.mapper.LoanMapper;
import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.service.EntityService;
import io.github.paulooorg.api.service.LoanService;

@Path("loan")
public class LoanResource extends AbstractGenericListResource<LoanDTO, Loan> {
	@Inject
	private LoanService loanService;
	
	@Override
	public EntityMapper<LoanDTO, Loan> getMapper() {
		return LoanMapper.INSTANCE;
	}

	@Override
	public EntityService<LoanDTO, Loan> getService() {
		return loanService;
	}
}
