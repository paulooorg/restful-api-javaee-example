package io.github.paulooorg.api.service.simulation;

import java.util.Optional;

import javax.inject.Inject;

import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.model.dto.SimulationDTO;
import io.github.paulooorg.api.model.entities.Client;
import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.model.entities.Modality;
import io.github.paulooorg.api.model.entities.Money;
import io.github.paulooorg.api.service.ClientService;
import io.github.paulooorg.api.service.LoanService;
import io.github.paulooorg.api.service.ModalityService;

public class SimulationService {
	@Inject
	private ModalityService modalityService;
	
	@Inject
	private ClientService clientService;
	
	@Inject
	private LoanService loanService;

	public SimulationService() {
	}

	@Deprecated
	public SimulationService(ModalityService modalityService, ClientService clientService) {
		this.modalityService = modalityService;
		this.clientService = clientService;
	}

	public Loan simulate(SimulationDTO simulationDTO) {
		Loan loan = createLoan(simulationDTO);
		loan.simulate();
		return loanService.findById(loanService.create(loan)).get();
	}
	
	private Loan createLoan(SimulationDTO simulationDTO) {
		Loan loan = new Loan();
		loan.setAmount(new Money(simulationDTO.getAmount()));
		loan.setFirstPaymentDate(simulationDTO.getFirstPaymentDate());
		loan.setTermInMonths(simulationDTO.getTermInMonths());
		
		Optional<Client> client = clientService.findById(simulationDTO.getClientId());
		if (client.isPresent()) {
			loan.setClient(client.get());
		} else {
			throw new BusinessException("clientIdNotFound", new Object[] {simulationDTO.getClientId()});
		}
		
		Optional<Modality> modality = modalityService.findById(simulationDTO.getModalityId());
		if (modality.isPresent()) {
			loan.setModality(modality.get());
		} else {
			throw new BusinessException("modalityIdNotFound", new Object[] {simulationDTO.getModalityId()});
		}
		
		return loan;
	}
}
