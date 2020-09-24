package io.github.paulooorg.api.resources;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.infrastructure.hateoas.LinkDTO;
import io.github.paulooorg.api.model.dto.LoanDTO;
import io.github.paulooorg.api.model.dto.SimulationDTO;
import io.github.paulooorg.api.model.dto.mapper.LoanMapper;
import io.github.paulooorg.api.service.simulation.SimulationService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("simulation")
public class SimulationResource {
	@Inject
	private SimulationService simulationService;
	
	@Context
	private UriInfo uriInfo;
	
	@POST
	public LoanDTO simulate(SimulationDTO simulationDTO) {
		LoanDTO loanDTO = LoanMapper.INSTANCE.entityToDTO(simulationService.simulate(simulationDTO));
		loanDTO.setLinks(createLinks(loanDTO));
		return loanDTO;
	}
	
	private List<LinkDTO> createLinks(LoanDTO loanDTO) {
		return Arrays.asList(new LinkDTO(Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
				.path(LoanResource.class)
				.path(loanDTO.getId().toString()))
				.rel("self").build(), HttpMethod.GET));
	}
}
