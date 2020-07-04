package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import io.github.paulooorg.api.model.dto.SimulationDTO;
import io.github.paulooorg.api.service.simulation.SimulationService;

@Path("simulation")
public class SimulationResource {
	@Inject
	private SimulationService simulationService;
	
	@POST
	public Long simulate(SimulationDTO simulationDTO) {
		return simulationService.simulate(simulationDTO).getId();
	}
}
