package io.github.paulooorg.api.service.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.model.dto.SimulationDTO;
import io.github.paulooorg.api.model.entities.Client;
import io.github.paulooorg.api.model.entities.Modality;
import io.github.paulooorg.api.service.ClientService;
import io.github.paulooorg.api.service.ModalityService;

public class SimulationServiceTest {
	private SimulationService simulationService;

	@Mock
	private ClientService clientService;
	
	@Mock
	private ModalityService modalityService;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void shouldThrowsExceptionWhenClientNotFound() {
		BusinessException exception = assertThrows(BusinessException.class, () -> {
			when(clientService.findById(anyLong())).thenReturn(Optional.empty());
			when(modalityService.findById(anyLong())).thenReturn(Optional.ofNullable(new Modality()));
			simulationService = new SimulationService(modalityService, clientService);
			simulationService.simulate(new SimulationDTO());
		});
		assertEquals("clientIdNotFound", exception.getI18n());
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void shouldThrowsExceptionWhenModalityNotFound() {
		BusinessException exception = assertThrows(BusinessException.class, () -> {
			when(clientService.findById(anyLong())).thenReturn(Optional.ofNullable(new Client()));
			when(modalityService.findById(anyLong())).thenReturn(Optional.empty());
			simulationService = new SimulationService(modalityService, clientService);
			simulationService.simulate(new SimulationDTO());
		});
		assertEquals("modalityIdNotFound", exception.getI18n());
	}
}
